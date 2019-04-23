import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {
    private Position position;

    @Before
    public void setup(){
        position = new Position();
    }

    @After
    public void teardown(){
        position = null;
    }

    // Testing PositionToString

    @Test
    public void TestPositionToString_shouldPrintPositionToString(){
        position = new Position(1,1);

        Assert.assertEquals("Position{x=1, y=1}",position.toString());
    }
}
