import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLOutput;
import java.util.Random;

class Map {

    int mapSize;

    //The map will consist of 2d array of tiles along with each tile's type
    int[][][] tiles;

    //Below are counters used to hold the current number of tiles with the corresponding tile type
    //The use of these is so that the program does not have more than one treasure or a large amount of water tiles
    int grassCount = 0;
    private int waterCount = 0;
    private int treasureCount = 0;

    //Constructor for the map object
    Map(){

    }

    //This method is used on start up to create the map
    //All tiles are randomly assigned a tile type
    void generate(){

        tiles = new int[mapSize][mapSize][1];

        Random random = new Random();

        //This value holds the tile type (grass, water, treasure)which corresponds to a random number from 0 to 2
        int tileNum;

        //This check is used to make sure that no tileSet is full
        boolean tileSetFull = false;

        //The maximum number of water tiles in a map is set to one less than mapSize
        double waterMaxTiles = Math.ceil(mapSize-1);

        //The maximum number of treasure tiles is one
        int treasureMaxTiles = 1;

        //Loop through the entire tile set and add a random value from 0 to 2 to the given tile to set the tile type
        for (int i = 0; i < mapSize; i++) {
            for (int j = 0; j < mapSize; j++) {

                do {
                    //The random number is obtained first using the Random class
                    tileNum = random.nextInt(3);

                    //A switch statement is used to go through each of the possible tile types
                    switch(tileNum){

                        //Grass tile
                        case 0:
                            tiles[i][j][0] = 0;

                            //The counter is updated since another grass tile has been added to the map
                            grassCount += 1;

                            //This check is set to false since this tile set is not full
                            tileSetFull = false;

                            break;

                        //Water tile
                        case 1:
                            if (waterCount == waterMaxTiles) {
                                //When the maximum number of water tiles is reached the check is set to true
                                //This is so that the while loop will continue until a random number is obtained which
                                //is not full
                                tileSetFull = true;
                            }

                            else {
                                tiles[i][j][0] = 1;

                                //The counter is updated since another water tile has been added to the map
                                waterCount += 1;

                                //This check is set to false since this tile set is not full
                                tileSetFull = false;
                            }

                            break;

                        //Treasure tile
                        case 2:

                            if (treasureCount == treasureMaxTiles) {
                                //When a treasure tile is already placed the check is set to true
                                tileSetFull = true;
                            }

                            else {
                                tiles[i][j][0] = 2;

                                //The counter is updated since a treasure tile has been added to the map
                                treasureCount += 1;

                                //This check is set to false since this tile set is not full
                                tileSetFull = false;

                            }

                            break;

                        default:
                            //This case is accessed only when a random number which is not 0,1 or 2 is obtained
                            System.err.println("Invalid random number obtained");
                    }


                }
                //The loop keeps on iterating until a valid tile type can be assigned to the tile
                while (tileSetFull);
            }
        }
    }

    //Method used to show the map
    void showMap(){
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                System.out.println("(" + x  + "," + y + ") -> " + tiles[x][y][0]);
            }
        }
    }

    //This method is used to create a 2d array which holds the map location of all grass tiles
    int[][] getGrassTiles(){

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
    int getTileType(int x, int y){
        return tiles[x][y][0];
    }

    //This method handles the events of when a player lands on each specific tile type
    void evaluateCurrentPlayerTile(Player player){

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
