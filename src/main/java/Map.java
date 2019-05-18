public interface Map {

    //This method is used to return the map size
    int getMapSizeVar();

    //This method is used to manually change the map size
    void setMapSize(int mapSize);

    //This method is used to return the tiles in the map
    int[][][] getTiles();

    //This method is used to manually change the tiles in the map
    void setTiles(int[][][] tiles);

    //This method is used to return the amount of water tiles in the map
    int getWaterCount();

    //This method is used to return the amount of grass tiles in the map
    int getGrassCount();

    //This method is used to return the amount of treasure tiles in the map
    int getTreasureCount();

    //This method is used to create the map
    void generate();

    //Method to obtain the current number of elements in the generatedTiles array
    int generatedTilesNum(boolean[][][] array);

    //This method is used to create a 2d array which holds the map location of all grass tiles
    int[][] getGrassTiles();

    //This method is used to get the tile type of the current tile
    int getTileType(int x, int y);

    //This method handles the events of when a player lands on each specific tile type
    void evaluateCurrentPlayerTile(Player player);
}
