import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

class Player {

    //Player's current position
    Position position;

    //Player toString
    @Override
    public String toString() {
        return "Player{" +
                "position=" + position +
                '}';
    }

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


    //Method used to obtain the last position the player moved
    Position getLastPosition(){
        return positions.get(positions.size() - 1);
    }

    //Method used to get the last n directions
    String getPreviousDirections(){
        String directions;
        StringBuilder stringBuilder = new StringBuilder();

        //Get the size of the players previous directions
        int directionSize = this.directions.size();

        //Loop for the last 6 directions the player has moved
        for(int i = 1; i <= 6; i++){
            //If only one direction has been entered
            if(directionSize == 1){
                stringBuilder.append(" ").append(this.directions.get(directionSize - 1));
                break;
            }
            //If more than 1 directions have been entered
            else if (directionSize >1){
                //Add direction unless there are less than 6 total directions
                if(directionSize - i <0){
                    break;
                }
                stringBuilder.append(" ").append(this.directions.get(directionSize - i));
            }
        }

        directions = stringBuilder.toString();

        return directions;
    }
}
