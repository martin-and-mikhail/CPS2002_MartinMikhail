import java.util.ArrayList;
import java.util.Random;

public class Player {

    Position position;

    //This array list is used to hold the previous positions the player
    ArrayList<Position> positions = new ArrayList<Position>();

    //This array list is used to hold the previous directions of the player
    ArrayList<String> directions = new ArrayList<String>();

    //Check to see if a player has found the treasure
    boolean foundTreasure;

    Player(){

    }

    Player(Position position){
       this.position = position;

    }

    //Method used to pass a new position by value
    public void addToPositions(int posx, int posy){

        Position position = new Position(posx, posy);

        positions.add(position);
        //This just used
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(positions);
    }

    //The player moves to the next tile depending on the inputted direction
    void move(char direction){

        //A switch statement is used to represent all possible directions
        switch(direction){
            case 'l':
                // change player's position
                this.position.x --;
                // add position to list of previous positions
                addToPositions(position.x, position.y);
                //positions.add(pos);
                // add direction to list of previous directions
                directions.add("left");
                break;

            case 'r':
                this.position.x ++;
                addToPositions(position.x, position.y);
                directions.add("right");
                break;

            case 'u':
                this.position.y --;
                addToPositions(position.x, position.y);
                directions.add("up");
                break;

            case 'd':
                this.position.y ++;
                addToPositions(position.x, position.y);
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
