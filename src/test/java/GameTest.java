import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Scanner;

public class GameTest {
    Game game;

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

    // testing checkOutOfBounds

    @Test
    public void TestDirectionBounds_testMovingUpOutOfBounds_shouldReturn0(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(4,0);
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('u',player, map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftOutOfBounds_shouldReturn0(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(0,3);
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('l',player, map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingRightOutOfBounds_shouldReturn0(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(map.mapSize-1,2);
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('r',player, map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingDownOutOfBounds_shouldReturn0(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(2,(map.mapSize-1));
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('d',player, map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingUpCorrectly_shouldReturn1(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(0,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('u',player, map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftCorrectly_shouldReturn1(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(2,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('l',player, map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingRightCorrectly_shouldReturn1(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(1,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('r',player, map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingDownCorrectly_shouldReturn1(){
        Map map = new Map();
        map.mapSize = 5;
        Position position = new Position(4,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('d',player, map.mapSize));
    }

    // testing validateDirectionInput

    @Test
    public void TestDirectionInput_testNonCharInput_shouldCatchRuntimeErrorAndReturny(){
        Scanner scanner = new Scanner("3");
        Assert.assertEquals('y',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testTooLongInput_shouldCatchRuntimeErrorAndReturny(){
        Scanner scanner = new Scanner("66bg");
        Assert.assertEquals('y',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testIncorrectChar_shouldReturnx(){
        Scanner scanner = new Scanner("t");
        Assert.assertEquals('x',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testu_shouldReturnu(){
        Scanner scanner = new Scanner("u");
        Assert.assertEquals('u',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testd_shouldReturnd(){
        Scanner scanner = new Scanner("d");
        Assert.assertEquals('d',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testl_shouldReturnl(){
        Scanner scanner = new Scanner("l");
        Assert.assertEquals('l',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testr_shouldReturnr(){
        Scanner scanner = new Scanner("r");
        Assert.assertEquals('r',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testU_shouldReturnu(){
        Scanner scanner = new Scanner("U");
        Assert.assertEquals('u',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testD_shouldReturnd(){
        Scanner scanner = new Scanner("D");
        Assert.assertEquals('d',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testL_shouldReturnl(){
        Scanner scanner = new Scanner("L");
        Assert.assertEquals('l',game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testR_shouldReturnr(){
        Scanner scanner = new Scanner("R");
        Assert.assertEquals('r',game.validateDirectionInput(scanner));
    }
}
