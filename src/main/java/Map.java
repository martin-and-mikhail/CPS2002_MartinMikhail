import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.SQLOutput;
import java.util.Random;

public class Map {

    //The map size is needed so as to create the n by n map
    private int mapSize;

    //The map will consist of 2d array of tiles with every tile having a weight
    private int[][][] tiles;

    //Below are counters used to hold the current number of tiles with the corresponding tile type
    //The use of these is so that the program does not have more than one treasure or a large amount of
    //water tiles
    int grassCount = 0;
    int waterCount = 0;
    int treasureCount = 0;

    public Map(){
    }

    //The getter for the mapSize
    public int getMapSize(){
        return mapSize;
    }

    //The setter for the mapSize
    public void setMapSize(int mapSize){
        this.mapSize = mapSize;
    }

    //The method is used to randomly allocate the tile types of all the tiles at the start of the game
    private void setTiles(){

        Random random = new Random();

        //This value holds the tile type which corresponds to a random number from 0 to 2
        int tileNum;

        //This check is used to make sure that no tileSet is full
        boolean tileSetFull = false;

        //The maximum number of watrer tiles in a map is set to a fifth of the whole map
        double waterMaxTiles = Math.ceil(mapSize*mapSize/5.0);

        //The maximum number of treasure tiles is one
        double treasureMaxTiles = 1;

        //Loop through the entire tile set and add a random value from 0 to 2 to the given tile to set the tile type
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {

                do {
                    //The random number is obtained first using the Random class
                    tileNum = random.nextInt(3);

                    //A switch statement is used to go through each of the possible tile types
                    switch(tileNum){

                        case 0:
                            //This case is accessed when the tile type is grass
                            tiles[x][y][0] = 0;

                            //The counter is updated since another grass tile is added to the map
                            grassCount += 1;

                            //This check is set to false since this tile set is not full
                            tileSetFull = false;

                            break;

                        case 1:
                            //This case accessed is when the tile type is water

                            if (waterCount == waterMaxTiles) {

                                //When the maximum number of water tiles is reached the check is set to true
                                //This is so that the while loop continue until a random number is obtained which
                                //is not full
                                tileSetFull = true;
                            }

                            else {

                                //Similar logic as the one for grass is used here
                                tiles[x][y][0] = 1;
                                waterCount += 1;
                                tileSetFull = false;
                            }

                            break;

                        case 2:
                            //This case is accessed when the tile type is treasure

                            if (treasureCount == treasureMaxTiles) {

                                //When a treasure tile is already placed the check is set to true
                                tileSetFull = true;
                            }

                            else {
                                //Similar logic as the one used for grass and water is used here
                                tiles[x][y][0] = 2;
                                tileSetFull = false;
                                treasureCount += 1;
                            }

                            break;

                        default:
                            //This case is accessed only when a random number which is not 0,1 or 2 is obtained
                            System.out.println("Invalid random number obtained");
                        }


                }
                //The loop keeps on iterating until the tile type obtained does not have its
                while (tileSetFull);
            }
        }

    }

    //This method is used to return the tile type of the current tile
    private void getTileType(int tileNum){

        switch (tileNum){

            case 0:
                System.out.println("This is a grass tile");
                break;

            case 1:
                System.out.println("This is a water tile");
                break;

            case 2:
                System.out.println("This is a treasure tile");
                break;
        }

    }

    //Method used to show the map
    private void showMap(){

        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {
                System.out.println("(" + x  + "," + y + ") -> " + tiles[x][y][0]);
            }
        }
    }

    //This method is used on start up to create the map
    public void generate(){

        System.out.println("Map is being generated");

        tiles = new int[mapSize][mapSize][1];

        setTiles();

        //showMap();

        System.out.println("Tiles for map are set with the corresponding tile type");


        //Random allocation of the tile set
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

    //Comapres the current player tile with a tile the tiles in the grid to obtain the tile type
    //Then execute the event associated with each tile
    public void compareTiles(Player player){

        //variable which holds the tile type
        int tileType;

        //Now we have obtained the tile type of the current tile the player is on
        tileType = tiles[player.position.x][player.position.y][0];

        switch(tileType){
            case 0:
                grassTileEvent();
                break;

            case 1:
                waterTileEvent(player);
            break;

            case 2:
                treasureTileEvent(player);
                break;

        }

    }

    //Event which occurs when a player is on a grass tile
    void grassTileEvent(){
        // do nothing really as position stays the same so probably delete this method l8r
    }

    //Event which occure when a player is on a water tile
    void waterTileEvent(Player player){

        //The position of the current player is set to the starting position which is saved in the positions array list
        player.position = player.positions.get(0);

    }

    void treasureTileEvent(Player player){
        //Do something to stop game and make the current player win
    }





}
