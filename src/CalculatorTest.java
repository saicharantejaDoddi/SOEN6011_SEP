
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CalculatorTest {

  /**
   * This Method tests checkInput Method in case of Correct input.
   */
  @Test
  void testCorrectFormat() {
    String userInput = "1234";
    Double actual = Calculator.checkInput(userInput);
    Double expected = new Double(1234);
    Assert.assertTrue(actual.doubleValue() == expected.doubleValue());
  }

  /**
   * This Method tests checkInput Method in case of InCorrect input.
   */
  @Test
  void testIncorrectFormat() {
    String userInput = "WrongFormat";
    Double actual = new Double(0);
    actual = Calculator.checkInput(userInput);
    Double expected = null;
    Assert.assertTrue(actual == expected);
  }

  /**
   * This Method tests calculateGamma Method in case of positive Integer input.
   */
  @Test
  void testPositiveIntegerForGammaFunction() {
    double userInput = 141;
    double expected = org.apache.commons.math3.special.Gamma.gamma(userInput);
    double actual = Calculator.calculateGamma(userInput);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.001);
  }

  /**
   * This Method tests calculateGamma Method in case of positive real number input.
   */
  @Test
  void testPositiveRealNumberForGammaFunction() {
    double userInput = 120.5;
    double expected = org.apache.commons.math3.special.Gamma.gamma(userInput);
    double actual = Calculator.calculateGamma(userInput);
    double percentError = (Math.abs((actual - expected)) / (expected)) * 100;
    Assert.assertTrue(percentError < 0.001);
  }

  /**
   * This Method tests calculateGamma Method in case of negative Integer input.
   */
  @Test
  void testNegativeIntegersForGammaFunction() {
    double userInput = -5;
    Double actual = new Double(Calculator.calculateGamma(userInput));
    Assert.assertTrue(actual.isInfinite());
  }

  /**
   * This Method tests calculateGamma Method in case of negative real number input.
   */
  @Test
  void testNegativeRealNumberForGammaFunction() {
    double userInput = -120.5;
    double expected = org.apache.commons.math3.special.Gamma.gamma(userInput);
    double actual = Calculator.calculateGamma(userInput);
    double percentError = (Math.abs((actual - expected)) / (expected)) * 100;
    Assert.assertTrue(percentError < 0.001);
  }

  /**
   * This Method tests calculateGamma Method in case of Positive limit input.
   */
  @Test
  void testPositiveLimitForGammaFunction() {
    double userInput = 170.1;
    Double actual = new Double(Calculator.calculateGamma(userInput));
    Assert.assertTrue(actual.isNaN());
  }

  /**
   * This Method tests calculateGamma Method in case of negative limit input.
   */
  @Test
  void testNegativeLimitForGammaFunction() {
    double userInput = -170.1;
    Double actual = new Double(Calculator.calculateGamma(userInput));
    Assert.assertTrue(actual.isNaN());
  }

  /**
   * This Method tests squareRoot Method.
   */
  @Test
  void testForsquareRoot() {
    double userInput = 158;
    double expected = Math.sqrt(userInput);
    double actual = Calculator.squareRoot(userInput);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests lnFirstCase Method.
   */
  @Test
  void testForlnFirstCase() {
    double userInput = 0.5;
    double expected = Math.log(userInput);
    double actual = Calculator.lnFirstCase(userInput);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests powerWithInteger Method.
   */
  @Test
  void testForpowerWithInteger() {

    double expected = Math.pow(5, 8);
    double actual = Calculator.powerWithInteger(5, 8);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests lnSecondCase Method.
   */
  @Test
  void testForlnSecondCase() {
    double userInput = 20;
    double expected = Math.log(20);
    double actual = Calculator.lnSecondCase(userInput);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests powerWithDouble Method.
   */
  @Test
  void testForpowerWithDouble() {

    double expected = Math.pow(5, 8.8);
    double actual = Calculator.powerWithDouble(5, 8.8);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests exponential Method.
   */
  @Test
  void testForexponential() {
    double userInput = 15;
    double expected = Math.exp(userInput);
    double actual = Calculator.exp(userInput);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests factorial Method.
   */
  @Test
  void testForfactorial() {
    int userInput = 5;
    double expected = 120;
    double actual = Calculator.factorial(userInput);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests sinValue Method.
   */
  @Test
  void testForsinValue() {
    int userInput = 50;
    double expected = Math.sin(userInput);
    double actual = Calculator.sinValue(userInput);
    double percentError = ((Math.abs((actual - expected)) / (expected)) * 100);
    Assert.assertTrue(percentError < 0.000001);
  }

  /**
   * This Method tests computeNewValue Method.
   */
  @Test
  void testForcomputeNewValue() {
    double expected = 1.33;
    double actual = Calculator.computeNewValue(2, 3, 2);
    double absoluteError = (Math.abs((actual - expected)));
    Assert.assertTrue(absoluteError < 0.01);
  }

}
