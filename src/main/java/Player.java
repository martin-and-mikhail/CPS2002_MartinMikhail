import java.util.ArrayList;
import java.util.Random;

class Player {

    //Player's current position
    Position position;

    //This array list is used to hold the previous positions the player
    ArrayList<Position> positions = new ArrayList<Position>();

    //This array list is used to hold the previous directions of the player
    ArrayList<String> directions = new ArrayList<String>();

    //Check to see if a player has found the treasure
    boolean foundTreasure;

    //Constructor for the player object
    Player(){
    }

    //Constructor for the player object when the player's position is given
    Player(Position position){
       this.position = position;
    }

    //Method used to add a position to the positions ArrayList using the x and y values
    void addToPositions(int posx, int posy){
        Position position = new Position(posx, posy);
        positions.add(position);
    }

    //Method to move the player's position according to a given direction
    void move(char direction){

        //A switch statement is used to represent all possible directions
        switch(direction){
            case 'l':
                // change player's position
                this.position.x --;
                // add position to list of previous positions
                addToPositions(position.x, position.y);
                // add direction to list of previous directions
                directions.add("left");
                break;

            case 'r':
                // change player's position
                this.position.x ++;
                // add position to list of previous positions
                addToPositions(position.x, position.y);
                // add direction to list of previous directions
                directions.add("right");
                break;

            case 'u':
                // change player's position
                this.position.y --;
                // add position to list of previous positions
                addToPositions(position.x, position.y);
                // add direction to list of previous directions
                directions.add("up");
                break;

            case 'd':
                // change player's position
                this.position.y ++;
                // add position to list of previous positions
                addToPositions(position.x, position.y);
                // add direction to list of previous directions
                directions.add("down");
                break;

            default:
                break;
        }
    }

    //This method sets the starting position of a player
    Position setStartingPosition(int[][] grassTiles){

        Random random = new Random();

        Position position = new Position(0, 0);

        //Obtaining the length of grassTiles so as to be able to know from which range to obtain a random number
        int grassCount = grassTiles.length;

        //random number from 0 to length of grassTiles is obtained
        int grassTilesIndex = random.nextInt(grassCount);

        //The start position is set
        position.x = grassTiles[grassTilesIndex][0];
        position.y = grassTiles[grassTilesIndex][1];

        //The current position of the player is set to the start position
        this.position = position;

        //The start positions is added to the created player
        addToPositions(position.x, position.y);

        return position;
    }

    //This method is used in the game class to check if a player has already been on a specific tile
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
