import java.util.LinkedList;
import java.util.Scanner;

/**
 * This class implements the gamma functionality.
 * 
 * @author SAI CHARAN TEJA DODDI
 * @version 1.0
 */
public class Calculator {

  static final double PIE_VALUE = 3.141592653589793;
  static final double E_VALUE = 2.718281828459045;
  static final String INVALID = "The input is invalid";
  static final String EXIT_WARN = "Please enter only 0(Continue) or 1(Exit) or 2(History)";
  static final String EXIT = "Do you want to exit?( 0 for Continue/1 for Exit/2 for History)";
  static final String ENTER = "Please Enter the value for gamma Function:";
  static final String NOT_WORK = "Please enter a value " + "between -170 and 170(Inclusive)";
  static final String INFINITY = "Infinity";
  static final String CORRECT_FORMAT = "Please enter only numerical value.";
  static LinkedList<String> history = new LinkedList<String>();

  /**
   * This is the starting point of the execution of the gamma functionality.
   * 
   * @param args value to be send from the command arguments.
   */
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    boolean close = false;
    int exit = 0;
    while (close == false && exit == 0) {
      System.out.println(Calculator.ENTER);

      boolean completed = false;

      while (completed == false) {
        String presentString = "";
        String userInput = sc.nextLine();
        Double desiredInput = new Double(0);
        desiredInput = Calculator.checkInput(userInput);
        if (desiredInput == null) {
          System.out.println(Calculator.CORRECT_FORMAT);
        } else {
          Double result = Calculator.calculateGamma(desiredInput);

          if (result.isNaN()) {
            System.out.println(Calculator.NOT_WORK);
          } else if (result.isInfinite()) {
            System.out.println(Calculator.INFINITY);
            completed = true;
          } else {
            System.out.println(result);
            presentString = "Gamma(" + desiredInput + ")=" + result;
            Calculator.history.add(presentString);
            completed = true;

          }
        }
      }

      int correctExit = 0;
      System.out.println(Calculator.EXIT);
      while (correctExit == 0) {

        try {
          exit = sc.nextInt();
          if (exit == 2) {
            Calculator.printHistory();
            System.out.println(Calculator.EXIT);
          } else {
            correctExit = 1;
          }

        } catch (Exception e) {
          System.out.println(Calculator.EXIT_WARN);
          sc.nextLine();
        }
      }


      sc.nextLine();
    }
    sc.close();
  }

  /**
   * This Method prints the history of the current user.
   */
  public static void printHistory() {
    for (int i = 0; i < Calculator.history.size(); i++) {
      System.out.println(Calculator.history.get(i));
    }
  }

  /**
   * This Method checks validity of the input.
   * 
   * @param userInput Value entered by the user.
   * @return the user input to a desired form.
   */
  public static Double checkInput(String userInput) {
    Double desiredInput = new Double(0);
    try {
      desiredInput = Double.valueOf(userInput);
    } catch (Exception e) {
      desiredInput = null;
      return desiredInput;
    }
    return desiredInput;
  }

  /**
   * This Method calculate the Positive Gamma value of the input to the method.
   * 
   * @param userInput Value for which Gamma to be calculated.
   * @return Gamma value of the input.
   */
  public static double calculateGamma(double userInput) {
    // Accurate
    // 141.8 goes to Infinity 171.7 (Infinity)
    // -141.8 goes to Infinity -170.7 (Infinity)
    if (userInput == 0) {
      return Double.POSITIVE_INFINITY;
    }
    if (userInput <= 170 && userInput >= -170) {
      if (userInput < 0) {
        double inputValue = 0 - (userInput);
        if (inputValue % 1 != 0) {
          double gammaValue = Calculator.calculateNegativeGamma(inputValue);
          // System.out.println("FROM PROGRAM "+gammaValue);
          return gammaValue;
        } else {
          // System.out.println(Calculator.Infinity);
          return Double.POSITIVE_INFINITY;
        }

      } else {
        double gammaValue = Calculator.calculatePositiveGamma(userInput);
        // System.out.println("FROM PROGRAM "+gammaValue);
        return gammaValue;
      }
    } else {
      // System.out.println(Calculator.NOT_WORK);
      return Double.NaN;
    }
  }

  /**
   * This Method calculate the Positive Gamma value of the input to the method.
   * 
   * @param input Value for which Gamma to be calculated.
   * @return Gamma value of the input.
   */
  public static double calculatePositiveGamma(double input) {
    double firstTerm = 0;
    if (input < 1) {
      firstTerm = (input) * Calculator.lnFirstCase(input);
    } else {
      firstTerm = (input) * Calculator.lnSecondCase(input);
    }
    double secondTerm = input;
    double partialThridTerm = Calculator.squareRoot((2 * Calculator.PIE_VALUE) / secondTerm);
    double thridTerm;
    if (partialThridTerm < 1) {
      thridTerm = Calculator.lnFirstCase(partialThridTerm);
    } else {
      thridTerm = Calculator.lnSecondCase(partialThridTerm);
    }
    double fourthTerm = (1 / (1188 * (Calculator.powerWithInteger(input, 9))));
    double fifthTerm = (1 / (1680 * (Calculator.powerWithInteger(input, 7))));
    double sixthTerm = (1 / (1260 * (Calculator.powerWithInteger(input, 5))));
    double seventhTerm = (1 / (360 * (Calculator.powerWithInteger(input, 3))));
    double eightTerm = (1 / (12 * (Calculator.powerWithInteger(input, 1))));
    double output = firstTerm - secondTerm + thridTerm + fourthTerm - fifthTerm + sixthTerm
        - seventhTerm + eightTerm;
    return Calculator.exp(output);
  }

  /**
   * This Method calculate the Negative Gamma value of the input to the method.
   * 
   * @param inputValue for which Gamma to be calculated.
   * @return Gamma value of the input.
   */
  public static double calculateNegativeGamma(double inputValue) {
    double denominatorSecond = Calculator.calculatePositiveGamma(inputValue);
    double sinToDegress = (Calculator.PIE_VALUE * inputValue) % (2 * Calculator.PIE_VALUE);
    double denominator = denominatorSecond * inputValue * Calculator.sinValue(sinToDegress);
    double output = (-Calculator.PIE_VALUE) / denominator;
    return output;
  }

  /**
   * This Method calculate the SquareRoot value of the input to the method.
   * 
   * @param input Value for which SquareRoot to be calculated.
   * @return SquareRoot value of the input.
   */
  public static double squareRoot(double input) {
    double error = 0.00001;
    double errorPrecision = 1;
    double duplicate = input;

    while ((errorPrecision) > error) {
      input = (input + duplicate / input) / 2;
      errorPrecision = input - duplicate / input;
    }
    return input;
  }

  /**
   * This Method calculate the natural Log (In Case <1) to the input value.
   * 
   * @param inputValue for which natural Log (In Case <1)to be calculated.
   * @return Natural Log value of the input.
   */
  public static double lnFirstCase(double inputValue) {
    double firstTerm = inputValue - 1;
    double outputValue = 0;
    double currentTerm = inputValue;

    for (int i = 1; (currentTerm > 1E-306) || (-currentTerm) > (1E-306); i++) {
      double numerator = Calculator.powerWithInteger(firstTerm, i);
      double denominator = i;
      currentTerm = numerator / denominator;
      if (i % 2 == 0) {
        currentTerm = -currentTerm;
      } else {
        currentTerm = +currentTerm;
      }
      outputValue = outputValue + currentTerm;
    }
    return outputValue;

  }

  /**
   * This Method calculate the Base value to the Power value Integer.
   * 
   * @param base input Value for which Power to be calculated.
   * @param power Power Value of the input to be calculated.
   * @return Base value to the Power value.
   */
  public static double powerWithInteger(double base, int power) {
    double outputValue = 1;

    for (int i = 1; i <= power; i++) {
      outputValue = outputValue * base;
    }

    return outputValue;
  }

  /**
   * This Method calculate the natural Log (In Case >1) to the input value.
   * 
   * @param input Value for which natural Log (In Case >1)to be calculated.
   * @return Natural Log value of the input.
   */
  public static double lnSecondCase(double input) {
    double output = 0;
    double denominator = 0;
    double x = input / (input - 1);
    double currentTerm = x;
    output = (1 / currentTerm);
    for (int i = 2; currentTerm > 1E-306; i++) {
      denominator = (i) * Calculator.powerWithInteger(x, i);
      currentTerm = 1 / (denominator);
      output = output + currentTerm;
    }
    return output;
  }

  /**
   * This Method calculate the Base value to the Power value.
   * 
   * @param base input Value for which Power to be calculated.
   * @param power Power Value of the input to be calculated.
   * @return Base value to the Power value.
   */
  public static double powerWithDouble(double base, double power) {
    double output = 0;
    if (base < 1) {
      output = (power * Calculator.lnFirstCase(base));
    } else {
      output = (power * Calculator.lnSecondCase(base));
    }
    return Calculator.exp(output);
  }

  /**
   * This Method calculate the Exponential value of the input to the method.
   * 
   * @param input Value for which exponential to be calculated.
   * @return exponential value of the input.
   */
  public static double exp(double input) {
    double output = 1;
    double oldValue = 1;

    for (int i = 1; (oldValue > 1E-306); i++) {

      double newValue = Calculator.computeNewValue(oldValue, i, input);
      oldValue = newValue;
      output = output + oldValue;
    }
    return output;
  }

  /**
   * This Method calculate the next value in the series using the previous value.
   * 
   * @param previousValue the value of the previous term in the series.
   * @param order the order of the term to be computed.
   * @param numerator the numerator of the term.
   * @return new value of in the series.
   */
  public static double computeNewValue(double previousValue, double order, double numerator) {
    double newnumerator = previousValue * numerator;
    double newValue = newnumerator / order;
    return newValue;
  }

  /**
   * This Method calculate the Factorial value of the input to the method.
   * 
   * @param input Value for which Factorial to be calculated.
   * @return Factorial value of the input.
   */

  public static double factorial(int input) {
    double output = 1;
    for (int i = input; i >= 1; i--) {
      output = output * i;
    }
    return output;


  }

  /**
   * This Method calculate the sin value of the input to the method.
   * 
   * @param input Value for which Sin is to be calculated.
   * @return Sin value of the input.
   */

  public static double sinValue(double input) {
    double output = 0;
    double oldValue = 1;
    boolean operator = true;
    for (int i = 1; (oldValue > 1E-306); i++) {
      double newValue = Calculator.computeNewValue(oldValue, i, input);
      oldValue = newValue;
      if (i % 2 != 0) {
        if (operator == true) {
          output = output + oldValue;
          operator = false;
        } else if (operator == false) {
          operator = true;
          output = output - oldValue;
        }
      }

    }
    return output;
  }

}
