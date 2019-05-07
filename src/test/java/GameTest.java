import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static junit.framework.TestCase.fail;

public class GameTest {
    private Game game;

    @Before
    public void setup() {
        game = new Game();
    }

    @After
    public void teardown() {
        game = null;
    }

    // testing validatePlayerNum
    @Test
    public void TestPlayerNum_testStringInput_shouldCatchInputMismatchExceptionAndReturn0() {
        // simulate user input
        Scanner scanner = new Scanner("testString");
        // check if correct error value is thrown
        Assert.assertEquals(0, game.validatePlayerNum(scanner));
    }

    @Test
    public void TestPlayerNum_testBelowMinPlayerNum_shouldReturn1() {
        Scanner scanner = new Scanner("1");
        Assert.assertEquals(1, game.validatePlayerNum(scanner));
    }

    @Test
    public void TestPlayerNum_testAboveMaxPlayerNum_shouldReturn1() {
        Scanner scanner = new Scanner("10");
        Assert.assertEquals(1, game.validatePlayerNum(scanner));
    }

    @Test
    public void TestPlayerNum_testCorrectPlayerNum_shouldReturnNum() {
        Scanner scanner = new Scanner("5");
        Assert.assertEquals(5, game.validatePlayerNum(scanner));
    }

    // testing validateTeamNum

    @Test
    public void TestTeamNum_testStringInput_shouldCatchInputMismatchExceptionAndReturn0() {
        // simulate user input
        Scanner scanner = new Scanner("testString");
        // check if correct error value is thrown
        Assert.assertEquals(0, game.validateTeamNum(scanner));
    }

    @Test
    public void TestTeamNum_testBelowMinTeamNum_shouldReturn1() {
        Scanner scanner = new Scanner("1");
        Assert.assertEquals(1, game.validateTeamNum(scanner));
    }

    @Test
    public void TestTeamNum_testAboveMaxTeamNum_shouldReturn1() {
        game.playerNum = 5;
        Scanner scanner = new Scanner("10");
        Assert.assertEquals(1, game.validateTeamNum(scanner));
    }

    @Test
    public void TestTeamNum_testCorrectTeamNum_shouldReturnNum() {
        game.playerNum = 5;
        Scanner scanner = new Scanner("3");
        Assert.assertEquals(3, game.validateTeamNum(scanner));
    }

    // testing validateMapSize
    @Test
    public void TestMapSize_testStringInput_shouldCatchInputMismatchExceptionAndReturn0() {
        Scanner scanner = new Scanner("testString");
        Assert.assertEquals(0, game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testAboveMaxMapSize_shouldReturn1() {
        Scanner scanner = new Scanner("52");
        Assert.assertEquals(1, game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testBelowMinMapSizeFirstRange_shouldReturn2() {
        game.playerNum = 4;
        Scanner scanner = new Scanner("3");
        Assert.assertEquals(2, game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testBelowMinMapSizeSecondRange_shouldReturn3() {
        game.playerNum = 8;
        Scanner scanner = new Scanner("7");
        Assert.assertEquals(3, game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testCorrectMapSizeFirstRange_shouldReturnNum() {
        game.playerNum = 3;
        Scanner scanner = new Scanner("6");
        Assert.assertEquals(6, game.validateMapSize(scanner));
    }

    @Test
    public void TestMapSize_testCorrectMapSizeSecondRange_shouldReturnNum() {
        game.playerNum = 7;
        Scanner scanner = new Scanner("20");
        Assert.assertEquals(20, game.validateMapSize(scanner));
    }

    //testing validateMapType

    @Test
    public void TestMapType_testStringInput_shouldCatchInputMismatchExceptionAndReturn0() {
        Scanner scanner = new Scanner("testString");
        Assert.assertEquals(0, game.validateMapType(scanner));
    }

    @Test
    public void TestMapType_testInvalidNumber_shouldReturn3() {
        Scanner scanner = new Scanner("52");
        Assert.assertEquals(3, game.validateMapType(scanner));
    }

    @Test
    public void TestMapType_test1_shouldReturn1() {
        Scanner scanner = new Scanner("1");
        Assert.assertEquals(1, game.validateMapType(scanner));
    }

    @Test
    public void TestMapType_test2_shouldReturn2() {
        Scanner scanner = new Scanner("2");
        Assert.assertEquals(2, game.validateMapType(scanner));
    }

    // testing checkOutOfBounds

    @Test
    public void TestDirectionBounds_testMovingUpOutOfBoundsSafeMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(4, 0);
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('u', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftOutOfBoundsSafeMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(0, 3);
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('l', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingRightOutOfBoundsSafeMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(game.map.getMapSizeVar() - 1, 2);
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('r', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingDownOutOfBoundsSafeMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(2, (game.map.getMapSizeVar() - 1));
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('d', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingUpCorrectlySafeMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(0, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('u', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftCorrectlySafeMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(2, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('l', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingRightCorrectlySafeMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(1, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('r', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingDownCorrectlySafeMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("safe", 5);

        Position position = new Position(4, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('d', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingUpOutOfBoundsHazardousMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(4, 0);
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('u', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftOutOfBoundsHazardousMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(0, 3);
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('l', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingRightOutOfBoundsHazardousMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(game.map.getMapSizeVar() - 1, 2);
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('r', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingDownOutOfBoundsHazardousMap_shouldReturn0() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(2, (game.map.getMapSizeVar() - 1));
        Player player = new Player(position);
        Assert.assertEquals(0, game.checkOutOfBounds('d', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingUpCorrectlyHazardousMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(0, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('u', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingLeftCorrectlyHazardousMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(2, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('l', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingRightCorrectlyHazardousMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(1, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('r', player, game.map.getMapSizeVar()));
    }

    @Test
    public void TestDirectionBounds_testMovingDownCorrectlyHazardousMap_shouldReturn1() {
        MapCreator creator = new MapCreator();
        game.map = creator.createMap("hazardous", 5);

        Position position = new Position(4, 3);
        Player player = new Player(position);
        Assert.assertEquals(1, game.checkOutOfBounds('d', player, game.map.getMapSizeVar()));
    }

    // testing validateDirectionInput

    @Test
    public void TestDirectionInput_testNonCharInput_shouldCatchRuntimeErrorAndReturny() {
        Scanner scanner = new Scanner("3");
        Assert.assertEquals('y', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testTooLongInput_shouldCatchRuntimeErrorAndReturny() {
        Scanner scanner = new Scanner("66bg");
        Assert.assertEquals('y', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testIncorrectChar_shouldReturnx() {
        Scanner scanner = new Scanner("t");
        Assert.assertEquals('x', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testu_shouldReturnu() {
        Scanner scanner = new Scanner("u");
        Assert.assertEquals('u', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testd_shouldReturnd() {
        Scanner scanner = new Scanner("d");
        Assert.assertEquals('d', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testl_shouldReturnl() {
        Scanner scanner = new Scanner("l");
        Assert.assertEquals('l', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testr_shouldReturnr() {
        Scanner scanner = new Scanner("r");
        Assert.assertEquals('r', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testU_shouldReturnu() {
        Scanner scanner = new Scanner("U");
        Assert.assertEquals('u', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testD_shouldReturnd() {
        Scanner scanner = new Scanner("D");
        Assert.assertEquals('d', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testL_shouldReturnl() {
        Scanner scanner = new Scanner("L");
        Assert.assertEquals('l', game.validateDirectionInput(scanner));
    }

    @Test
    public void TestDirectionInput_testR_shouldReturnr() {
        Scanner scanner = new Scanner("R");
        Assert.assertEquals('r', game.validateDirectionInput(scanner));
    }

    //Testing HTML file deletion

    @Test
    public void TestDeleteHtmlFile_testDeleteSingleFile_shouldCreateFileAndDeleteIt() {
        //create the file
        File file = new File("map.html");
        try {
            if (!file.createNewFile()) {
                fail("File could not be created");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //make sure that the file exists
        Assert.assertTrue(file.exists());

        //delete the file
        game.deleteHtmlFile();

        //make sure that the file no longer exists
        Assert.assertFalse(file.exists());
    }

    //Testing validateExitChar

    @Test
    public void TestValidateExitChar_testNonCharInput_shouldCatchRuntimeErrorAndReturny() {
        Scanner scanner = new Scanner("3");
        Assert.assertEquals('y', game.validateExitChar(scanner));
    }

    @Test
    public void TestExitChar_testTooLongInput_shouldCatchRuntimeErrorAndReturny() {
        Scanner scanner = new Scanner("66bg");
        Assert.assertEquals('y', game.validateExitChar(scanner));
    }

    @Test
    public void TestExitChar_testIncorrectChar_shouldReturnx() {
        Scanner scanner = new Scanner("t");
        Assert.assertEquals('x', game.validateExitChar(scanner));
    }

    @Test
    public void TestExitChar_testCorrectInput_shouldReturne() {
        Scanner scanner = new Scanner("e");
        Assert.assertEquals('e', game.validateExitChar(scanner));
    }

    // testing generateTeam

    @Test
    public void TestGenerateTeam_testPlayersCanBeSortedEqually_specificallyTest6PlayersInto3Teams_shouldCreate3TeamsEachWith2Players() {
        //create instance of map creator
        MapCreator creator = new MapCreator();

        //create map of chosen type and size using the creator classes
        Map map = creator.createMap("safe", 8);

        game.playerNum = 6;
        game.teamNum = 3;

        //The remainder of the total number of players divided by the total number of teams is obtained
        int extraPlayersNum = game.playerNum % game.teamNum;

        //The total number of player per team is obtained excluding the extra players
        int playersInTeamNum = (game.playerNum - extraPlayersNum)/game.teamNum;

        //In this loop all the Player objects are created along with their starting position in the map
        for (int i = 0; i < game.playerNum; i++) {

            //A new player object is made
            Player player = new Player();

            //The random position of the player is set to a grass tile
            player.position = player.setStartingPosition(map.getGrassTiles());

            //The created player is added to the ArrayList of players
            game.players.add(player);
        }

        //Holds the players which have been added to a team
        ArrayList<Player> addedPlayers = new ArrayList<Player>();

        //Now to assign all the player objects to a random team
        for (int i = 0; i < game.teamNum; i++) {

            //A new team is created
            Team team;

            //Generate the team
            team = game.generateTeam(addedPlayers, playersInTeamNum);

            //Add the team to the list of teams in the game
            game.teams.add(team);
        }

        //ensure that 3 teams have been created
        Assert.assertEquals(3, game.teams.size());

        //ensure that all 3 teams have 2 players each
        for(Team team: game.teams){
            Assert.assertEquals(2, team.players.size());
        }
    }

    @Test
    public void TestGenerateTeam_testPlayersCannotBeSortedEqually_specificallyTest5PlayersInto3Teams_shouldCreate3TeamsWith2Or1Players() {
        //create instance of map creator
        MapCreator creator = new MapCreator();

        //create map of chosen type and size using the creator classes
        Map map = creator.createMap("safe", 8);

        game.playerNum = 5;
        game.teamNum = 3;

        //The remainder of the total number of players divided by the total number of teams is obtained
        int extraPlayersNum = game.playerNum % game.teamNum;

        //The total number of player per team is obtained excluding the extra players
        int playersInTeamNum = (game.playerNum - extraPlayersNum)/game.teamNum;

        //In this loop all the Player objects are created along with their starting position in the map
        for (int i = 0; i < game.playerNum; i++) {

            //A new player object is made
            Player player = new Player();

            //The random position of the player is set to a grass tile
            player.position = player.setStartingPosition(map.getGrassTiles());

            //The created player is added to the ArrayList of players
            game.players.add(player);
        }

        //Holds the players which have been added to a team
        ArrayList<Player> addedPlayers = new ArrayList<Player>();

        //Now to assign all the player objects to a random team
        for (int i = 0; i < game.teamNum; i++) {

            //A new team is created
            Team team;

            //Generate the team
            team = game.generateTeam(addedPlayers, playersInTeamNum);

            //Add the team to the list of teams in the game
            game.teams.add(team);
        }

        //Since the players are not all evenly distributed among the teams
        game.distributeRemainder(addedPlayers, extraPlayersNum);

        //ensure that 3 teams have been created
        Assert.assertEquals(3, game.teams.size());

        //ensure that from the 2 teams, 2 have 2 players and 1 has 1
        int twoPlayers = 0;
        int onePlayer = 0;

        for(Team team: game.teams){
            if(team.players.size() == 2){
                twoPlayers++;
            }
            else if(team.players.size() == 1){
                onePlayer++;
            }
        }

        Assert.assertEquals(2, twoPlayers);
        Assert.assertEquals(1, onePlayer);
    }

    //Testing getTeamIndex
    @Test
    public void TestGetTeamIndex_testPlayerInTeam1_shouldReturn1() {
        Position position = new Position(0,0);
        Player player = new Player(position);
        Team team = new Team();
        game.teams.add(team);
        team.players.add(player);

        Assert.assertEquals(0, game.getTeamIndex(player));
    }

    @Test
    public void TestGetTeamIndex_testPlayerNotInATeam_shouldReturnNegative1() {
        Position position = new Position(0,0);
        Player player = new Player(position);

        Assert.assertEquals(-1, game.getTeamIndex(player));
    }
}