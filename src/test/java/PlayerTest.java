import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.instanceOf;


public class PlayerTest {

    private Player player;

    @Before
    public void setup(){
        player = new Player();
    }

    @After
    public void teardown(){
        player = null;
    }

    //Testing creating a player with a position and toString
    @Test
    public void TestCreatePlayerWithPosition_shouldCreatePlayer(){
        Position position = new Position(0,0);
        player = new Player(position);

        Assert.assertEquals("Player{position=Position{x=0, y=0}}", player.toString());
    }

    // Testing AddToPositions

    @Test
    public void TestAddToPositions_testWhenArrayListIsEmpty_shouldAddGivenPositionsToArrayList(){
        Position position1 = new Position(0,0);

        //Expected list of positions after we have added position (0,0)
        Position[] expectedPositions = new Position[]{position1};

        //Adding position (0,0) to list of positions
        player.addToPositions(0,0);

        Assert.assertEquals(player.positions.toString(), Arrays.toString(expectedPositions));
    }

    @Test
    public void TestAddToPositions_testWhenArrayListIsNotEmpty_shouldAddGivenPositionsToArrayList(){
        Position position1 = new Position(0,0);
        Position position2 = new Position(1,1);

        //Expected list of positions after we have added positions (0,0) and (1,1)
        Position[] expectedPositions = new Position[]{position1,position2};

        player.addToPositions(0,0);
        player.addToPositions(1,1);

        Assert.assertEquals(player.positions.toString(), Arrays.toString(expectedPositions));
    }

    // Testing Move

    @Test
    public void TestPlayerMove_testRight_shouldMovePositionOneTileToTheRight(){
        //Initialise player's position
        player.position = new Position(0,0);

        //What we expect the position to be after moving right
        Position positionExpectedAfter = new Position(1,0);

        //Carry out movement
        player.move('r');

        Assert.assertEquals(player.position.toString(), positionExpectedAfter.toString());
    }

    @Test
    public void TestPlayerMove_testLeft_shouldMovePositionOneTileToTheLeft(){
        //Initialise player's position
        player.position = new Position(1,0);

        //What we expect the position to be after moving right
        Position positionExpectedAfter = new Position(0,0);

        //Carry out movement
        player.move('l');

        Assert.assertEquals(player.position.toString(), positionExpectedAfter.toString());
    }

    @Test
    public void TestPlayerMove_testUp_shouldMovePositionOneTileUpwards(){
        //Initialise player's position
        player.position = new Position(1,1);

        //What we expect the position to be after moving right
        Position positionExpectedAfter = new Position(1,0);

        //Carry out movement
        player.move('u');

        Assert.assertEquals(player.position.toString(), positionExpectedAfter.toString());
    }

    @Test
    public void TestPlayerMove_testDown_shouldMovePositionOneTileDownwards(){
        //Initialise player's position
        player.position = new Position(0,0);

        //What we expect the position to be after moving right
        Position positionExpectedAfter = new Position(0,1);

        //Carry out movement
        player.move('d');

        Assert.assertEquals(player.position.toString(), positionExpectedAfter.toString());
    }

    @Test
    public void TestPlayerMove_testInvalidDirection_positionShouldNotChange(){
        //Initialise player's position
        player.position = new Position(0,0);

        //What we expect the position to be after moving with an invalid direction
        Position positionExpectedAfter = new Position(0,0);

        //Carry out invalid movement
        player.move('x');

        Assert.assertEquals(player.position.toString(), positionExpectedAfter.toString());
    }

    //Testing setStartingPosition

    @Test
    public void TestSetStartingPosition_shouldReturnPosition(){
        int[][] grassTiles = new int[][]{{0,0},{0,1}};

        //Check that the method returns a position
        Assert.assertNotNull(player.setStartingPosition(grassTiles));
    }

    //Testing ifTileExists

    @Test
    public void TestIfTileExists_testingTileWhichPlayerHasSteppedOn_shouldReturnTrue(){
        player.addToPositions(0,0);
        player.addToPositions(0,1);

        //Check that the method returns a position
        Assert.assertTrue(player.ifTileExists(0,1));
    }

    @Test
    public void TestIfTileExists_testingTileWhichPlayerHasNotSteppedOn_shouldReturnFalse(){
        player.addToPositions(0,0);
        player.addToPositions(0,1);

        //Check that the method returns a position
        Assert.assertFalse(player.ifTileExists(1,1));
    }
}
