import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setup(){
        calculator = new Calculator();
    }

    @After
    public void teardown(){
        calculator = null;
    }

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
}
