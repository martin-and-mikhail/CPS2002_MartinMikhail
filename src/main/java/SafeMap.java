import java.util.Random;

public class SafeMap implements Map{

    //Size of the map. Total squares will be mapSize x mapSize
    private int mapSize;

    //getter for mapSize
    public int getMapSizeVar() {
        return mapSize;
    }

    //setter for mapSize
    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    //The map will consist of 2d array of tiles along with each tile's type
    private int[][][] tiles;

    //getter for tiles
    public int[][][] getTiles() {
        return tiles;
    }

    //setter for tiles
    public void setTiles(int[][][] tiles) {
        this.tiles = tiles;
    }

    //Below are counters used to hold the current number of tiles with the corresponding tile type
    //The use of these is so that the program does not have more than one treasure or a large amount of water tiles
    private int grassCount = 0;
    private int waterCount = 0;
    private int treasureCount = 0;

    //getter for grassCount
    public int getGrassCount() {
        return grassCount;
    }

    //getter for waterCount
    public int getWaterCount() {
        return waterCount;
    }

    //getter for treasureCount
    public int getTreasureCount() {
        return treasureCount;
    }

    //Constructor for SafeMap
    SafeMap(int mapSize){
        this.mapSize = mapSize;
    }

    //Method to generate a safe map
    public void generate(){

        tiles = new int[mapSize][mapSize][1];

        Random random = new Random();

        //Holds the current random tile obtained
        int[] randomPair = new int[2];

        //Holds a boolean value which determines if the [i][j] tile has already been generated for the map
        boolean[][][] generatedTiles = new boolean[mapSize][mapSize][1];

        //This value holds the tile type (grass, water, treasure)which corresponds to a random number from 0 to 2
        int tileType;

        //The maximum number of water tiles in a map is set to one less than mapSize
        int waterMaxTiles = (int) Math.floor((mapSize*mapSize) * 0.1);

        //The maximum number of treasure tiles is one
        int treasureMaxTiles = 1;

        //Initialising the generatedTiles array
        for(int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {
                generatedTiles[i][j][0] = false;
            }
        }

        //Keep on looping until all tiles are randomly generated
        while(generatedTilesNum(generatedTiles) != mapSize*mapSize){

            //Randomly generate a pair of tiles
            //Numbers generated will be from 0 to mapSize-1
            randomPair[0] = random.nextInt(mapSize);
            randomPair[1] = random.nextInt(mapSize);

            //Now checking if the tile has already been obtained in the generated tiles
            //This is done by checking if the boolean value of the tile is true
            if(generatedTiles[randomPair[0]][randomPair[1]][0]){
                //Tile has already been generated
                //So the user must get another tile which has not already been generated

                //Keeps on looping until a new tile is generated
                while(generatedTiles[randomPair[0]][randomPair[1]][0]){

                    randomPair[0] = random.nextInt(mapSize);
                    randomPair[1] = random.nextInt(mapSize);

                }
            }

            //Keep on looping until the current tile is given a tile type
            do {

                //Set the tile type for the newly generated tile
                //A random number from 0 to 2 is obtained which correspond to a tile type
                tileType = random.nextInt(3);

                //A switch statement is used to go through each of the possible tile types
                switch (tileType) {

                    //Grass tile
                    case 0:
                        tiles[randomPair[0]][randomPair[1]][0] = 0;

                        //The counter is updated since another grass tile has been added to the map
                        grassCount += 1;

                        break;

                    //Water tile
                    case 1:
                        if (waterCount == waterMaxTiles) {
                            //When the maximum number of water tiles is reached the check is set to true
                            //This is so that the while loop will continue until a random number is obtained which
                            //is not full
                            continue;

                        } else {
                            tiles[randomPair[0]][randomPair[1]][0] = 1;

                            //The counter is updated since another water tile has been added to the map
                            waterCount += 1;
                        }
                        break;

                    //Treasure tile
                    case 2:

                        if (treasureCount == treasureMaxTiles) {
                            //When a treasure tile is already placed the check is set to true
                            continue;
                        } else {
                            tiles[randomPair[0]][randomPair[1]][0] = 2;
                            //The counter is updated since a teasure tile has been added to the map
                            treasureCount += 1;
                        }
                        break;

                    default:
                        //This case is accessed only when a random number which is not 0,1 or 2 is obtained
                        System.err.println("Invalid random number obtained");
                        break;

                }

                //add the the tile to the generatedTiles array
                generatedTiles[randomPair[0]][randomPair[1]][0] = true;
            }while( tiles[randomPair[0]][randomPair[1]][0] != 0 && tiles[randomPair[0]][randomPair[1]][0] != 1 && tiles[randomPair[0]][randomPair[1]][0] != 2);
        }
    }

    //Method to obtain the current number of elements in the generatedTiles array
    public int generatedTilesNum(boolean[][][] array){
        int count = 0;

        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                if(array[i][j][0]){
                    count++;
                }
            }
        }

        return count;
    }

    //This method is used to create a 2d array which holds the map location of all grass tiles
    public int[][] getGrassTiles(){

        //This array holds the number of grass elements each with 2 spaces for the x and y values of the given tile
        int[][] grassTiles = new int[grassCount][2];

        //Counter used to keep track of the element in grassTiles
        int i = 0;

        //Loop through all the tiles
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {

                //To obtain which tiles are grass just check the tile type indicated by a 0

                //If the current tile has a grass tile type
                if(tiles[x][y][0] == 0){

                    //Save the x and y coordinates in the grassTiles array
                    grassTiles[i][0] = x;
                    grassTiles[i][1] = y;

                    //The counter is incremented so as to be able to go to the next value in the array
                    i++;

                }
            }
        }
        return grassTiles;
    }

    //This method is used to get the tile type of the current tile
    public int getTileType(int x, int y){
        return tiles[x][y][0];
    }

    //This method handles the events of when a player lands on each specific tile type
    public void evaluateCurrentPlayerTile(Player player){

        int tileType = tiles[player.position.x][player.position.y][0];

        switch(tileType){

            //When grass tile
            case 0:
                //Nothing happens since a player is able to walk on grass
                break;

            //When water tile
            case 1:
                //Get the start position of the current player
                int startPositionx = player.positions.get(0).x;
                int startPositiony = player.positions.get(0).y;

                //The start position of the current player is added again to the visited tiles
                player.addToPositions(startPositionx, startPositiony);

                //The current position of the current player is reset to the start position
                player.position.x = startPositionx;
                player.position.y = startPositiony;

                //Display message
                System.out.println("Player stepped on a blue tile, moving back to starting position!");
                break;

            //When treasure tile
            case 2:
                //The foundTreasure element is set to true
                player.foundTreasure = true;
                break;

            default:
                break;
        }
    }
}
