import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

public class SafeMapTest{
    private Map map;

    @Before
    public void setup(){
        MapCreator creator = new MapCreator();
        map = creator.createMap("safe", 6);
    }

    @After
    public void teardown(){
        map = null;
    }

    //Testing mapSize getter
    @Test
    public void TestMapSizeGetter_shouldReturn6(){
        Assert.assertEquals(6, map.getMapSizeVar());
    }

    //Testing mapSize setter
    @Test
    public void TestMapSizeSetter_shouldReturn5(){
        //test before
        Assert.assertEquals(6, map.getMapSizeVar());
        map.setMapSize(5);

        //test after
        Assert.assertEquals(5, map.getMapSizeVar());
    }

    // Testing Map generation and tiles getter
    @Test
    public void TestGeneratedMap_shouldDisplayTilesIncluding3WaterTilesAnd1TreasureTile(){
        System.out.println(Arrays.deepToString(map.getTiles()));
    }

    //Testing tile setter
    @Test
    public void TestTileSetter_shouldReturnSetTileArray(){
        map.setTiles(new int[][][]{{{0},{0}},{{1},{2}}});
        Assert.assertEquals("[[[0], [0]], [[1], [2]]]",Arrays.deepToString(map.getTiles()));
    }

    //Testing that counts are properly initialised

    @Test
    public void TestCounts_shouldSetCorrectAmountofEachTile(){
        Assert.assertEquals(3, map.getWaterCount());
        Assert.assertEquals(1, map.getTreasureCount());
        Assert.assertEquals(32, map.getGrassCount());
    }

    //Testing getGrassTiles

    @Test
    public void TestGetGrassTiles_thereShouldBe32GrassTiles(){
        int i=0;
        for(int[] array : map.getGrassTiles()) {
            i++;
        }

        //ensure that 32 grass tiles have been found
        Assert.assertEquals(32,i);
    }


    //Testing getTileType

    @Test
    public void TestGetTileType_testGrassTile_shouldReturn0(){
        //simulate map with 4 tiles
        map.setMapSize(2);

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.setTiles(new int[][][]{{{0},{0}},{{1},{2}}});

        Assert.assertEquals(0,map.getTileType(0,0));
    }

    @Test
    public void TestGetTileType_testWaterTile_shouldReturn1(){
        //simulate map with 4 tiles
        map.setMapSize(2);

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.setTiles(new int[][][]{{{0},{0}},{{1},{2}}});

        Assert.assertEquals(1,map.getTileType(1,0));
    }

    @Test
    public void TestGetTileType_testTreasureTile_shouldReturn2(){
        //simulate map with 4 tiles
        map.setMapSize(2);

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.setTiles(new int[][][]{{{0},{0}},{{1},{2}}});

        Assert.assertEquals(2,map.getTileType(1,1));
    }


    //Test evaluateCurrentPlayerTile

    @Test
    public void TestEvaluateCurrentPlayerTile_testGrassTile_shouldDoNothing(){
        //simulate map with 4 tiles
        map.setMapSize(2);

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.setTiles(new int[][][]{{{0},{0}},{{1},{2}}});

        //Create player and place on grass tile
        Position position = new Position(0,0);
        Player player = new Player(position);

        //Process event of landing on grass
        map.evaluateCurrentPlayerTile(player);
    }

    @Test
    public void TestEvaluateCurrentPlayerTile_testWaterTile_shouldMoveBackToStartingPosition(){
        //simulate map with 4 tiles
        map.setMapSize(2);

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.setTiles(new int[][][]{{{0},{0}},{{1},{2}}});

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
        map.setMapSize(2);

        //create map with 2 grass tiles, a water tile and a treasure tile
        map.setTiles(new int[][][]{{{0}, {0}}, {{1}, {2}}});

        //Create player and place on grass tile
        Position position = new Position(1, 1);
        Player player = new Player(position);

        //Process event of landing on treasure
        map.evaluateCurrentPlayerTile(player);

        Assert.assertTrue(player.foundTreasure);
    }


    //Test generatedTilesNum

    @Test
    public void TestGeneratedTilesNum_shouldReturnNumberOfTiles(){
        //simulate map with 4 tiles
        map.setMapSize(2);

        //create map with 2 grass tiles, a water tile and a treasure tile
        boolean [][][] testArray = new boolean[][][]{{{true},{false}},{{false},{true}}};

        //Make sure that the 2 trues are read and counted
        Assert.assertEquals(2,map.generatedTilesNum(testArray));
    }
}
