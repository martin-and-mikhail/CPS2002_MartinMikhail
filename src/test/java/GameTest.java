import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;

public class GameTest {
    private Game game;

    @Before
    public void setup(){
        game = new Game();
    }

    @After
    public void teardown(){
        game = null;
    }

    // testing validatePlayerNum
    @Test
    public void TestPlayerNum_testStringInput_shouldCatchInputMismatchExceptionAndReturn0(){
        // simulate user input
        Scanner scanner = new Scanner("testString");
        // check if correct error value is thrown
        Assert.assertEquals(0,game.validatePlayerNum(scanner));
    }

    @Test
    public void TestPlayerNum_testBelowMinPlayerNum_shouldReturn1(){
        Scanner scanner = new Scanner("1");
        Assert.assertEquals(1,game.validatePlayerNum(scanner));
    }

    @Test
    public void TestPlayerNum_testAboveMaxPlayerNum_shouldReturn1(){
        Scanner scanner = new Scanner("10");
        Assert.assertEquals(1,game.validatePlayerNum(scanner));
    }

    @Test
    public void TestPlayerNum_testCorrectPlayerNum_shouldReturnNum(){
        Scanner scanner = new Scanner("5");
        Assert.assertEquals(5 ,game.validatePlayerNum(scanner));
    }

    // testing validateMapSize
    @Test
    public void TestMapSize_testStringInput_shouldCatchInputMismatchExceptionAndReturn0(){
        Scanner scanner = new Scanner("testString");
        Assert.assertEquals(0,game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testAboveMaxMapSize_shouldReturn1(){
        Scanner scanner = new Scanner("52");
        Assert.assertEquals(1,game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testBelowMinMapSizeFirstRange_shouldReturn2(){
        game.playerNum = 4;
        Scanner scanner = new Scanner("3");
        Assert.assertEquals(2,game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testBelowMinMapSizeSecondRange_shouldReturn3(){
        game.playerNum = 8;
        Scanner scanner = new Scanner("7");
        Assert.assertEquals(3,game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testCorrectMapSizeFirstRange_shouldReturnNum(){
        game.playerNum = 3;
        Scanner scanner = new Scanner("6");
        Assert.assertEquals(6,game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testCorrectMapSizeSecondRange_shouldReturnNum(){
        game.playerNum = 7;
        Scanner scanner = new Scanner("20");
        Assert.assertEquals(20,game.validateMapSize(scanner));
    }
}
