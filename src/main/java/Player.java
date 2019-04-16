import java.util.ArrayList;
import java.util.Random;

public class Player {

    Position position;

    //This array list is used to hold the previous positions of all players
    ArrayList<Position> positions = new ArrayList<Position>();

    public Player(){

    }

    public Player(Position position){
       this.position = position;

    }

    //The player moves to the next tile depending on the inputted direction
    void move(char direction){

        //A switch statement is used to represent all possible directions
        switch(direction){
            case 'l':
                // change player's position
                position.x --;
                // add position to list of previous positions
                positions.add(position);
                break;

            case 'r':
                position.x ++;
                positions.add(position);
                break;

            case 'u':
                position.y --;
                positions.add(position);
                break;

            case 'd':
                position.y ++;
                positions.add(position);
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
}
