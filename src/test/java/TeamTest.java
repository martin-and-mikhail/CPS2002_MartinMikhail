import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class TeamTest {
    private Team team;

    @Before
    public void setup(){
        team = new Team();
    }

    @After
    public void teardown(){
        team = null;
    }

    //Testing addPlayer

    @Test
    public void TestAddPlayer_testWhenArrayListIsEmpty_shouldAddGivenPlayerToArrayList(){
        Player player1 = new Player();

        //Expected list of positions after we have added a player
        Player[] expectedPlayers = new Player[]{player1};

        //Adding player1 to list of players
        team.addPlayer(player1);

        Assert.assertEquals(Arrays.toString(expectedPlayers), team.players.toString());
    }

    @Test
    public void TestAddPlayer_testWhenArrayListIsNotEmpty_shouldAddGivenPlayersToArrayList(){
        Player player1 = new Player();
        Player player2 = new Player();

        //Expected list of players after we have added player1 and player2
        Player[] expectedPlayers = new Player[]{player1,player2};

        team.addPlayer(player1);
        team.addPlayer(player2);

        Assert.assertEquals(Arrays.toString(expectedPlayers), team.players.toString());
    }

    // Testing AddToPositions

    @Test
    public void TestAddToPositions_testWhenArrayListIsEmpty_shouldAddGivenPositionsToArrayList(){
        Position position1 = new Position(0,0);

        //Expected list of positions after we have added position (0,0)
        Position[] expectedPositions = new Position[]{position1};
        System.out.println(Arrays.toString(expectedPositions));

        //Adding position (0,0) to list of positions
        team.addToPositions(0,0);

        Assert.assertEquals(Arrays.toString(expectedPositions), team.positions.toString());
    }

    @Test
    public void TestAddToPositions_testWhenArrayListIsNotEmpty_shouldAddGivenPositionsToArrayList(){
        Position position1 = new Position(0,0);
        Position position2 = new Position(1,1);

        //Expected list of positions after we have added positions (0,0) and (1,1)
        Position[] expectedPositions = new Position[]{position1,position2};

        team.addToPositions(0,0);
        team.addToPositions(1,1);

        Assert.assertEquals(Arrays.toString(expectedPositions), team.positions.toString());
    }

    //Testijng updateTeamPositions

    @Test
    public void TestUpdatePositions_testWhenPositionDoesNotExist_shouldBeAddedToList(){
        Position position1 = new Position(0,0);
        Player player = new Player(position1);

        //Expected list of positions after we have added position (0,0)
        Position[] expectedPositions = new Position[]{position1};

        //Adding position (0,0) to list of positions
        team.updateTeamPositions(player);

        Assert.assertEquals(Arrays.toString(expectedPositions), team.positions.toString());
    }

    @Test
    public void TestUpdatePositions_testWhenPositionExists_shouldNotBeAddedToList(){
        Position position1 = new Position(0,0);
        Player player1 = new Player(position1);
        Player player2 = new Player(position1);

        //Expected list of positions after we have added position (0,0)
        Position[] expectedPositions = new Position[]{position1};

        //Adding position (0,0) to list of positions
        team.updateTeamPositions(player1);

        //Trying to add the same position to the list
        team.updateTeamPositions(player2);

        //Make sure position has only been added once
        Assert.assertEquals(Arrays.toString(expectedPositions), team.positions.toString());
    }

    //Testing updateAllTeamPositions

    @Test
    public void TestUpdateAllPositions_testWhenPositionDoesNotExist_shouldBeAddedToList(){
        Position position1 = new Position(0,0);
        Player player1 = new Player(position1);
        Position position2 = new Position(1,1);
        Player player2 = new Player(position2);

        team.players.add(player1);
        team.players.add(player2);

        //Expected list of positions after we have added position (0,0)
        Position[] expectedPositions = new Position[]{position1,position2};

        //Adding position (0,0) to list of positions
        team.updateAllTeamPositions();

        Assert.assertEquals(Arrays.toString(expectedPositions), team.positions.toString());
    }

    @Test
    public void TestUpdateAllPositions_testWhenPositionExists_shouldNotBeAddedToList(){
        Position position1 = new Position(0,0);
        Player player1 = new Player(position1);
        Position position2 = new Position(1,1);
        Player player2 = new Player(position2);
        Position position3 = new Position(0,0);
        Player player3 = new Player(position3);

        team.players.add(player1);
        team.players.add(player2);
        team.players.add(player3);

        //Expected list of positions after we have added position (0,0)
        Position[] expectedPositions = new Position[]{position1,position2};

        //Adding position (0,0) to list of positions
        team.updateAllTeamPositions();

        Assert.assertEquals(Arrays.toString(expectedPositions), team.positions.toString());
    }

    //Testing positionDoesNotExist

    @Test
    public void TestPositionDoesNotExist_testWhenPositionExists_shouldReturnFalse(){
        Position position1 = new Position(0,0);

        team.positions.add(position1);

        Assert.assertFalse(team.positionDoesNotExist(position1));
    }

    @Test
    public void TestPositionDoesNotExist_testWhenPositionDoesNotExist_shouldReturnTrue(){
        Position position1 = new Position(0,0);
        Position position2 = new Position(1,1);

        team.positions.add(position1);

        Assert.assertTrue(team.positionDoesNotExist(position2));
    }


    //Testing change HtmlFiles

    @Test
    public void TestChangeHtmlFile_testSafeMap_shouldCreateFileAndReturn1() {
        Game game = new Game();
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(0,0);
        Player player = new Player(position);
        game.players.add(player);
        team.players.add(player);

        Assert.assertEquals(1, team.changeHtmlFile(0, game.map, player));

        game.deleteHtmlFile();
    }

    @Test
    public void TestChangeHtmlFile_testHazardousMap_shouldCreateFileAndReturn1() {
        Game game = new Game();
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(0,0);
        Player player = new Player(position);
        game.players.add(player);
        team.players.add(player);

        Assert.assertEquals(1, team.changeHtmlFile(0, game.map, player));

        game.deleteHtmlFile();
    }

    //Testing ifTileExists

    @Test
    public void TestIfTileExists_testFileExists_shouldReturnTrue() {

    }

}
