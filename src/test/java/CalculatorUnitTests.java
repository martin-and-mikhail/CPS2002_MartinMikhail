import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CalculatorUnitTests {
    Calculator calculator;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    // Addition Tests
    @Test
    public void TestAdd_testTwoPositiveNumbers_shouldBeAdding(){
        assertEquals(5, calculator.add(2,3));
    }

    @Test
    public void TestAdd_testPositiveAndNegative_shouldBeAdding(){
        assertEquals(-1, calculator.add(2,-3));
    }

    @Test
    public void TestAdd_testTwoNegativeNumbers_shouldBeAdding(){
        assertEquals(-5, calculator.add(-2,-3));
    }

    @Test
    public void TestAdd_testPositiveAndZero_shouldBeAdding(){
        assertEquals(2, calculator.add(2,0));
    }

    @Test
    public void TestAdd_testNegativeAndZero_shouldBeAdding(){
        assertEquals(-2, calculator.add(-2,0));
    }

    @Test
    public void TestAdd_TwoZeros_shouldBeAdding(){
        assertEquals(0, calculator.add(0,0));
    }

    // Subtraction Tests
    @Test
    public void TestSubtract_testTwoPositiveNumbers_shouldBeSubtracting(){
        assertEquals(-1, calculator.subtract(2,3));
    }

    @Test
    public void TestSubtract_testPositiveAndNegative_shouldBeSubtracting(){
        assertEquals(5, calculator.subtract(2,-3));
    }

    @Test
    public void TestSubtract_testTwoNegativeNumbers_shouldBeSubtracting(){
        assertEquals(1, calculator.subtract(-2,-3));
    }

    @Test
    public void TestSubtract_testPositiveAndZero_shouldBeSubtracting(){
        assertEquals(2, calculator.subtract(2,0));
    }

    @Test
    public void TestSubtract_testNegativeAndZero_shouldBeSubtracting(){
        assertEquals(-2, calculator.subtract(-2,0));
    }

    @Test
    public void TestSubtract_TwoZeros_shouldBeSubtracting(){
        assertEquals(0, calculator.subtract(0,0));
    }

    // Multiplication Tests
    @Test
    public void TestMultiply_testTwoPositiveNumbers_shouldBeMultiplying(){
        assertEquals(10, calculator.multiply(2,5));
    }

    @Test
    public void TestMultiply_testTwoNegativeNumbers_shouldBeMultiplying(){
        assertEquals(10, calculator.multiply(-2,-5));
    }

    @Test
    public void TestMultiply_testNegativeAndPositiveNumber_shouldBeMultiplying(){
        assertEquals(-10, calculator.multiply(-2,5));
    }

    @Test
    public void TestMultiply_multiplyWithZero_shouldBeMultiplying(){
        assertEquals(0, calculator.multiply(0,5));
    }

    @Test
    public void TestMultiply_testTwoZeroValues_shouldBeMultiplying(){
        assertEquals(0, calculator.multiply(0,0));
    }
}
