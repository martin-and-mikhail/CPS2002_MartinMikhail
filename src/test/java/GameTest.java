import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static junit.framework.TestCase.fail;

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

    // testing checkOutOfBounds

    @Test
    public void TestDirectionBounds_testMovingUpOutOfBounds_shouldReturn0(){
        game.map.mapSize = 5;
        Position position = new Position(4,0);
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('u',player, game.map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftOutOfBounds_shouldReturn0(){
        game.map.mapSize = 5;
        Position position = new Position(0,3);
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('l',player, game.map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingRightOutOfBounds_shouldReturn0(){
        game.map.mapSize = 5;
        Position position = new Position(game.map.mapSize-1,2);
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('r',player, game.map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingDownOutOfBounds_shouldReturn0(){
        game.map.mapSize = 5;
        Position position = new Position(2,(game.map.mapSize-1));
        Player player = new Player(position);
        Assert.assertEquals(0,game.checkOutOfBounds('d',player, game.map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingUpCorrectly_shouldReturn1(){
        game.map.mapSize = 5;
        Position position = new Position(0,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('u',player, game.map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftCorrectly_shouldReturn1(){
        game.map.mapSize = 5;
        Position position = new Position(2,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('l',player, game.map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingRightCorrectly_shouldReturn1(){
        game.map.mapSize = 5;
        Position position = new Position(1,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('r',player, game.map.mapSize));
    }

    @Test
    public void TestDirectionBounds_testMovingDownCorrectly_shouldReturn1(){
        game.map.mapSize = 5;
        Position position = new Position(4,3);
        Player player = new Player(position);
        Assert.assertEquals(1,game.checkOutOfBounds('d',player, game.map.mapSize));
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

    //Testing generateHtmlFiles

    @Test
    public void TestGenerateHtmlFiles_testNewFile_shouldCreateFileAndReturn1(){
        game.map.mapSize = 5;
        Position position = new Position(4,3);
        Player player = new Player(position);
        game.players.add(player);
        Assert.assertEquals(1, game.generateHtmlFile(0,game.map.mapSize, " "));

        game.deleteHtmlFiles(1);
    }

    @Test
    public void TestGenerateHtmlFiles_testNewFileWithDirection_shouldCreateFileAndReturn1(){
        game.map.mapSize = 5;
        Position position = new Position(4,3);
        Player player = new Player(position);
        game.players.add(player);
        Assert.assertEquals(1, game.generateHtmlFile(0,game.map.mapSize, "u"));

        game.deleteHtmlFiles(1);
    }

    @Test
    public void TestGenerateHtmlFiles_testOverwriteFile_shouldOverwriteExistingFileAndReturn2(){
        game.map.mapSize = 5;
        Position position = new Position(4,3);
        Player player = new Player(position);
        game.players.add(player);

        //create file
        game.generateHtmlFile(0,game.map.mapSize, " ");

        //check that overwriting file returns correct value
        Assert.assertEquals(2, game.generateHtmlFile(0,game.map.mapSize, " "));

        game.deleteHtmlFiles(1);
    }

    //Testing HTML file deletion

    @Test
    public void TestDeleteHtmlFiles_testDeleteSingleFile_shouldCreateFileAndDeleteIt(){
        //create the file
        File file = new File("map_player_1.html");
        try {
            if(!file.createNewFile()){
                fail("File could not be created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //make sure that the file exists
        Assert.assertTrue(file.exists());

        //delete the file
        game.deleteHtmlFiles(1);

        //make sure that the file no longer exists
        Assert.assertFalse(file.exists());
    }

    @Test
    public void TestDeleteHtmlFiles_testDeleteMultipleFiles_shouldCreateFilesAndDeleteThem(){
        //create the files
        File file = new File("map_player_1.html");
        try {
            if(!file.createNewFile()){
                fail("File could not be created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file2 = new File("map_player_2.html");
        try {
            if(!file2.createNewFile()){
                fail("File could not be created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //make sure that the files exist
        Assert.assertTrue(file.exists());
        Assert.assertTrue(file2.exists());

        //delete the file
        game.deleteHtmlFiles(2);

        //make sure that the files no longer exist
        Assert.assertFalse(file.exists());
        Assert.assertFalse(file2.exists());
    }

    //Testing validateExitChar

    @Test
    public void TestValidateExitChar_testNonCharInput_shouldCatchRuntimeErrorAndReturny(){
        Scanner scanner = new Scanner("3");
        Assert.assertEquals('y',game.validateExitChar(scanner));
    }

    @Test
    public void TestExitChar_testTooLongInput_shouldCatchRuntimeErrorAndReturny(){
        Scanner scanner = new Scanner("66bg");
        Assert.assertEquals('y',game.validateExitChar(scanner));
    }

    @Test
    public void TestExitChar_testIncorrectChar_shouldReturnx(){
        Scanner scanner = new Scanner("t");
        Assert.assertEquals('x',game.validateExitChar(scanner));
    }

    @Test
    public void TestExitChar_testCorrectInput_shouldReturne(){
        Scanner scanner = new Scanner("e");
        Assert.assertEquals('e',game.validateExitChar(scanner));
    }

    //Testing getPreviousDirections

    @Test
    public void TestGetPreviousDirections_testNoDirections_shouldOutputNothing(){
        //Add player to players Array List
        Player player = new Player();
        game.players.add(player);

        //Player is not given any directions

        //Ensure that an empty string is returned
        Assert.assertEquals("", game.getPreviousDirections(0));
    }

    @Test
    public void TestGetPreviousDirections_testSingleDirection_shouldOutputDirection(){
        //Add player to players Array List
        Player player = new Player();
        game.players.add(player);

        //Give player single direction
        player.directions.add("right");

        //Expected String to be returned by getPreviousDirection method
        String expected = " right";

        Assert.assertEquals(expected, game.getPreviousDirections(0));
    }

    @Test
    public void TestGetPreviousDirections_testLessThan6Directions_shouldOutputDirections(){
        //Add player to players Array List
        Player player = new Player();
        game.players.add(player);

        //Give player 4 directions
        player.directions.add("right");
        player.directions.add("left");
        player.directions.add("up");
        player.directions.add("down");

        //Expected String to be returned by getPreviousDirection method
        String expected = " down up left right";

        Assert.assertEquals(expected, game.getPreviousDirections(0));
    }

    @Test
    public void TestGetPreviousDirections_testOver6Directions_shouldOutputLast6Directions(){
        //Add player to players Array List
        Player player = new Player();
        game.players.add(player);

        //Give player 8 directions
        player.directions.add("up");
        player.directions.add("right");
        player.directions.add("down");
        player.directions.add("left");
        player.directions.add("right");
        player.directions.add("left");
        player.directions.add("up");
        player.directions.add("down");

        //Expected String to be returned by getPreviousDirection method
        String expected = " down up left right left down";

        Assert.assertEquals(expected, game.getPreviousDirections(0));
    }
}
