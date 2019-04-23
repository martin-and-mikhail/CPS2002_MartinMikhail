import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class MapTest {
    private Map map;

    @Before
    public void setup(){
        map = new Map();
    }

    @After
    public void teardown(){
        map = null;
    }

    // Testing Map generation

    @Test
    public void TestMapGenerate_shouldGenerateMapWithoutErrorsAndDisplayIt(){
        map.mapSize = 5;
        map.generate();
        System.out.println(Arrays.deepToString(map.tiles));
        map.showMap();
    }

    //Testing getGrassTiles

    @Test
    public void TestGetGrassTiles_testNoGrass_eachElementShouldBe0ByDefault(){
        //simulate map with a single tile
        map.mapSize = 1;
        map.tiles = new int [1][1][1];
        map.grassCount = 0;

        // set only tile to water
        map.tiles[0][0][0] = 1;
        System.out.println("map is " + Arrays.deepToString(map.tiles));

        for(int[] array : map.getGrassTiles()) {
            for (int num : array) {
                Assert.assertEquals(0, num);
            }
        }
    }

    @Test
    public void TestGetGrassTiles_testAllGrass_onlyElementShouldHaveGrassValue(){
        //simulate map with a single tile
        map.mapSize = 1;

        //create map with single grass tile
        map.tiles = new int[][][]{{{0}}};
        map.grassCount = 1;

        //grass tile position which we expect
        int[][] checker = new int[][]{{0,0}};

        //Should find a grass tile at position [0,0]
        Assert.assertArrayEquals(checker,map.getGrassTiles());
    }

    @Test
    public void TestGetGrassTiles_testSomeGrass_oneElementShouldHaveGrassValue(){
        //simulate map with 2 tiles
        map.mapSize = 2;
        map.grassCount = 1;

        //create map with single grass tile and 3 water tiles
        map.tiles = new int[][][]{{{1},{1}},{{0},{1}}};

        //grass tile position which we expect
        int[][] checker = new int[][]{{1,0}};

        Assert.assertArrayEquals(checker,map.getGrassTiles());
    }

    //Testing getTileType

    @Test
    public void TestGetTileType_testGrassTile_shouldReturn0(){
        //simulate map with 4 tiles
        map.mapSize = 2;

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.tiles = new int[][][]{{{0},{0}},{{1},{2}}};

        Assert.assertEquals(0,map.getTileType(0,0));
    }

    @Test
    public void TestGetTileType_testWaterTile_shouldReturn1(){
        //simulate map with 4 tiles
        map.mapSize = 2;

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.tiles = new int[][][]{{{0},{0}},{{1},{2}}};

        Assert.assertEquals(1,map.getTileType(1,0));
    }

    @Test
    public void TestGetTileType_testTreasureTile_shouldReturn2(){
        //simulate map with 4 tiles
        map.mapSize = 2;

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.tiles = new int[][][]{{{0},{0}},{{1},{2}}};

        Assert.assertEquals(2,map.getTileType(1,1));
    }

    //Test evaluateCurrentPlayerTile

    @Test
    public void TestEvaluateCurrentPlayerTile_testGrassTile_shouldDoNothing(){
        //simulate map with 4 tiles
        map.mapSize = 2;

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.tiles = new int[][][]{{{0},{0}},{{1},{2}}};

        //Create player and place on grass tile
        Position position = new Position(0,0);
        Player player = new Player(position);

        //Process event of landing on grass
        map.evaluateCurrentPlayerTile(player);
    }

    @Test
    public void TestEvaluateCurrentPlayerTile_testWaterTile_shouldMoveBackToStartingPosition(){
        //simulate map with 4 tiles
        map.mapSize = 2;

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.tiles = new int[][][]{{{0},{0}},{{1},{2}}};

        //Create player and place on grass tile
        Position startingPosition = new Position(0,0);
        Player player = new Player(startingPosition);
        player.positions.add(startingPosition);

        //Move player to water tile
        Position waterPosition = new Position(1,0);
        player.position = waterPosition;
        player.positions.add(waterPosition);

        //Check that player is at waterPosition
        Assert.assertEquals(player.position.toString(), waterPosition.toString());

        //Process event of landing on water
        map.evaluateCurrentPlayerTile(player);

        //Check that player is back at starting position
        Assert.assertEquals(player.position.toString(), startingPosition.toString());
    }

    @Test
    public void TestEvaluateCurrentPlayerTile_testTreasureTile_shouldSetFoundTreasureToTrue() {
        //simulate map with 4 tiles
        map.mapSize = 2;

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.tiles = new int[][][]{{{0}, {0}}, {{1}, {2}}};

        //Create player and place on grass tile
        Position position = new Position(1, 1);
        Player player = new Player(position);

        //Process event of landing on treasure
        map.evaluateCurrentPlayerTile(player);

        Assert.assertTrue(player.foundTreasure);
    }
}
