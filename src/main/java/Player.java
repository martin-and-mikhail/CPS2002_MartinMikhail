import java.util.Random;

public class Player {

    Position position;

    public Player(){

    }

    public Player(Position position){
       this.position = position;

    }

    //Need to obtain size of mapSize
    int mapSize;

    public void showPosition(){
        System.out.println("x is " + position.x);
        System.out.println("y is " + position.y);

    }

    void setMapSize(int mapSize){
        this.mapSize = mapSize;
    }

    //The player moves the next tile given the direction he inputs
    void move(char direction){

        //A switch statement is used to represent all possible directions
        switch(direction){
            case 'l':
                //Check if map limit going left is reached first
                //This is when x=0

                if(position.x == 0){
                    //Output to the user that the map limit has been reached
                }

                else{
                    //The x coordinate is decremented so that it looks like the player moved on tile back
                    position.x = position.x - 1;
                }

                break;

            case 'r':

                if(position.x == mapSize){

                }

                else{
                    position.x = position.x + 1;
                }
                break;

            case 'u':

                if(position.y == 0){
                    //Output to the user that the map limit has been reached
                }

                else{
                    //The x coordinate is decremented so that it looks like the player moved on tile back
                    position.y = position.y - 1;
                }

                break;

            case 'd':

                if(position.y == mapSize){
                    //Output to the user that the map limit has been reached
                }

                else{
                    //The x coordinate is decremented so that it looks like the player moved on tile back
                    position.y = position.y + 1;
                }
                break;

            default:
                //error handling here
                break;
        }

    }

    //This method sets the starting position of a player
    Position setStartingPosition(int[][] grassTiles){

        Random random = new Random();

        Position startPosition;

        //Obtaining the length of grassTiles so as to be able to know from which range to obtain a random number
        int grassCount = grassTiles.length;

        //random number from 0 to length of grassTiles is obtained
        int grassTilesIndex = random.nextInt(grassCount);

        //The start position is set
        startPosition = new Position(grassTiles[grassTilesIndex][0], grassTiles[grassTilesIndex][1]);

        //The current position of the player is set to the start position
        position = startPosition;

        return startPosition;
    }

}
