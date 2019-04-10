import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.Assert.*;

public class CalculatorUnitTests {
    private Calculator calculator;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    // Addition Tests
    @Test
    public void TestAdd_testTwoPositiveNumbers_shouldBeAdding(){
        Assert.assertEquals(5, calculator.add(2,3));
    }

    @Test
    public void TestAdd_testPositiveAndNegative_shouldBeAdding(){
        Assert.assertEquals(-1, calculator.add(2,-3));
    }

    @Test
    public void TestAdd_testTwoNegativeNumbers_shouldBeAdding(){
        Assert.assertEquals(-5, calculator.add(-2,-3));
    }

    @Test
    public void TestAdd_testPositiveAndZero_shouldBeAdding(){
        Assert.assertEquals(2, calculator.add(2,0));
    }

    @Test
    public void TestAdd_testNegativeAndZero_shouldBeAdding(){
        Assert.assertEquals(-2, calculator.add(-2,0));
    }

    @Test
    public void TestAdd_TwoZeros_shouldBeAdding(){
        Assert.assertEquals(0, calculator.add(0,0));
    }

    // Subtraction Tests
    @Test
    public void TestSubtract_testTwoPositiveNumbers_shouldBeSubtracting(){
        Assert.assertEquals(-1, calculator.subtract(2,3));
    }

    @Test
    public void TestSubtract_testPositiveAndNegative_shouldBeSubtracting(){
        Assert.assertEquals(5, calculator.subtract(2,-3));
    }

    @Test
    public void TestSubtract_testTwoNegativeNumbers_shouldBeSubtracting(){
        Assert.assertEquals(1, calculator.subtract(-2,-3));
    }

    @Test
    public void TestSubtract_testPositiveAndZero_shouldBeSubtracting(){
        Assert.assertEquals(2, calculator.subtract(2,0));
    }

    @Test
    public void TestSubtract_testNegativeAndZero_shouldBeSubtracting(){
        Assert.assertEquals(-2, calculator.subtract(-2,0));
    }

    @Test
    public void TestSubtract_TwoZeros_shouldBeSubtracting(){
        Assert.assertEquals(0, calculator.subtract(0,0));
    }

    @Test
    public void TestMultiply_testTwoPositiveNumbers_shouldBeMultiplying(){
        Assert.assertEquals(10, calculator.multiply(2,5));
    }

    @Test
    public void TestMultiply_testTwoNegativeNumbers_shouldBeMultiplying(){
        Assert.assertEquals(10, calculator.multiply(-2,-5));
    }

    @Test
    public void TestMultiply_testNegativeAndPositiveNumber_shouldBeMultiplying(){
        Assert.assertEquals(-10, calculator.multiply(-2,5));
    }

    @Test
    public void TestMultiply_multiplyWithZero_shouldBeMultiplying(){
        Assert.assertEquals(0, calculator.multiply(0,5));
    }

    @Test
    public void TestMultiply_testTwoZeroValues_shouldBeMultiplying(){
        Assert.assertEquals(0, calculator.multiply(0,0));
    }

    
}
