import java.util.ArrayList;
import java.util.Random;

public class Player {

    Position position;

    //This array list is used to hold the previous positions the player
    ArrayList<Position> positions = new ArrayList<Position>();

    //This array list is used to hold the previous directions of the player
    ArrayList<String> directions = new ArrayList<String>();

    public Player(){

    }

    public Player(Position position){
       this.position = position;

    }

    //The player moves to the next tile depending on the inputted direction
    void move(char direction){

        //A new object needs to be created so that the array list would not contain all the same items
        //Maybe needs to change after more turns
        //If needs to change just add a new object somewhere or the problem can be that the current position is not updated
        //However it is supposed to be ok
        Position position = new Position(this.position.x, this.position.y);

        //A switch statement is used to represent all possible directions
        switch(direction){
            case 'l':
                // change player's position
                this.position.x --;
                // add position to list of previous positions
                positions.add(position);
                // add direction to list of previous directions
                directions.add("left");
                break;

            case 'r':
                this.position.x ++;
                positions.add(position);
                directions.add("right");
                break;

            case 'u':
                this.position.y --;
                positions.add(position);
                directions.add("up");
                break;

            case 'd':
                this.position.y ++;
                positions.add(position);
                directions.add("down");
                break;

            default:
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

        //The start positions is added to the created player
        positions.add(startPosition);

        return startPosition;
    }

    //This method is used in the game class to check if the player already went on the current position in the map grid
    boolean ifTileExists(int x, int y){

        //Create the Position object to use to compare
        Position positionUse = new Position(x,y);

        //Looping through element in the positions array list
        for (Position position : positions) {

            //Comparing the x and y values of the current Position object in the array list and the positionUse object
            if(positionUse.x == position.x && positionUse.y == position.y){

                //If one of the obejct in the array list matched then it exists in the array list
                return true;
            }


        }
        return false;
    }
}
