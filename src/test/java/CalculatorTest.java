import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.junit.Assert;

public class CalculatorTest {
    Calculator calculator;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    @After
    public void teardown(){
        calculator = null;
    }

    // Addition Tests
    @Test
    public void TestAdd_testTwoPositiveNumbers_shouldBeAdding(){
        int a = 2, b = 3;
        Assert.assertEquals(2 + 3, calculator.add(a,b));
    }

    @Test
    public void TestAdd_testPositiveAndNegative_shouldBeAdding(){
        int a = 2, b = -3;
        Assert.assertEquals(2 + (-3), calculator.add(a,b));
    }

    @Test
    public void TestAdd_testTwoNegativeNumbers_shouldBeAdding(){
        int a = -2, b = -3;
        Assert.assertEquals((-2) + (-3), calculator.add(a,b));
    }

    @Test
    public void TestAdd_testPositiveAndZero_shouldBeAdding(){
        int a = 2, b = 0;
        Assert.assertEquals(2 + 0, calculator.add(a,b));
    }

    @Test
    public void TestAdd_testNegativeAndZero_shouldBeAdding(){
        int a = -2, b = 0;
        Assert.assertEquals((-2) + 0, calculator.add(a,b));
    }

    @Test
    public void TestAdd_TwoZeros_shouldBeAdding(){
        int a = 0, b = 0;
        Assert.assertEquals(0 + 0, calculator.add(a,b));
    }

    // Subtraction Tests
    @Test
    public void TestSubtract_testTwoPositiveNumbers_shouldBeSubtracting(){
        int a = 2, b = 3;
        Assert.assertEquals(2 - 3, calculator.subtract(a,b));
    }

    @Test
    public void TestSubtract_testPositiveAndNegative_shouldBeSubtracting(){
        int a = 2, b = -3;
        Assert.assertEquals(2 - (-3), calculator.subtract(a,b));
    }

    @Test
    public void TestSubtract_testTwoNegativeNumbers_shouldBeSubtracting(){
        int a = -2, b = -3;
        Assert.assertEquals((-2) - (-3), calculator.subtract(a,b));
    }

    @Test
    public void TestSubtract_testPositiveAndZero_shouldBeSubtracting(){
        int a = 2, b = 0;
        Assert.assertEquals(2 - 0, calculator.subtract(a,b));
    }

    @Test
    public void TestSubtract_testNegativeAndZero_shouldBeSubtracting(){
        int a = -2, b = 0;
        Assert.assertEquals(-2 - 0, calculator.subtract(a,b));
    }

    @Test
    public void TestSubtract_TwoZeros_shouldBeSubtracting(){
        int a = 0, b = 0;
        Assert.assertEquals(0 - 0, calculator.subtract(a,b));
    }

    // Multiplication Tests
    @Test
    public void TestMultiply_testTwoPositiveNumbers_shouldBeMultiplying(){
        int a = 2, b = 5;
        Assert.assertEquals(2 * 5, calculator.multiply(a,b));
    }

    @Test
    public void TestMultiply_testTwoNegativeNumbers_shouldBeMultiplying(){
        int a = -2, b = -5;
        Assert.assertEquals((-2) * (-5), calculator.multiply(a,b));
    }

    @Test
    public void TestMultiply_testNegativeAndPositiveNumber_shouldBeMultiplying(){
        int a = -2, b = 5;
        Assert.assertEquals((-2) * 5, calculator.multiply(a,b));
    }

    @Test
    public void TestMultiply_multiplyWithZero_shouldBeMultiplying(){
        int a = 0, b = 5;
        Assert.assertEquals(0*5, calculator.multiply(a,b));
    }

    @Test
    public void TestMultiply_testTwoZeroValues_shouldBeMultiplying(){
        int a = 0, b = 0;
        Assert.assertEquals(0*0, calculator.multiply(a,b));
    }
}
