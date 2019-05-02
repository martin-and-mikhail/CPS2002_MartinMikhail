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

    //Method used to change the main html file
    int changeHtmlFile(int playerIndex, Map map){
        //Value to return to mark if method has run successfully or not
        //Set to 1 by default. This will change to 0 if an error is encountered
        int returnValue = 1;

        String direction = getPreviousDirections();

        //This variable is used to hold the type of tile which the player has landed on
        int tileType;

        //This variable checks if the player is currently on this tile
        boolean playerHere;

        //A file object is being created where the name is given depending on the number of the player
        File file = new File("map.html");

        //The actual file is created here
        try {
            //If file already exists set return value to 2 to mark that it is being overwritten
            if(!file.createNewFile()){
                returnValue = 2;
            }
        } catch (IOException e) {
            e.printStackTrace();
            returnValue = 0;//Set return value to error
        }

        //This object is used to be able to add to the string easily
        StringBuilder htmlText = new StringBuilder();

        //This is the html code which is going to be placed in each file
        htmlText.append( "<!doctype html>\n" );
        htmlText.append( "<html>\n" );

        htmlText.append( "<head>\n" );
        htmlText.append( "<style>\n" );

        htmlText.append("div {\n" +
                //The width of the grid is set depending on the inputted map size
                //The height is larger than the width since we are also goign to have to count the header which is above the grid
                "    width: ").append(map.getMapSizeVar()).append("00px;\n")
                .append("    height: ").append(map.getMapSizeVar() + 1).append("00px;\n")
                .append("}\n")
                .append("\n")
                .append(".header {\n").append(
                //The width of the header is changed depending on the size of the map
                "  width: ").append(map.getMapSizeVar()).append("00px;\n")
                .append("  height: 100px;\n")
                .append("  outline: 1px solid;\n")
                .append("  float: left;\n")
                .append("  text-align: center;\n")
                .append("  background-color: #1f599a;\n")
                .append("  font-family: Arial, sans-serif;\n")
                .append("  font-size: 20px;\n")
                .append("  color: white;\n")
                .append("}\n")
                .append("\n")
                .append(".cellGray {\n")
                .append("    width: 100px;\n")
                .append("    height: 100px;\n")
                .append("    outline: 1px solid;\n")
                .append("    float: left;\n")
                .append("    background-color: Gray;\n")
                .append("}\n")
                .append("\n")
                .append(".cellGreen {\n")
                .append("  width: 100px;\n")
                .append("    height: 100px;\n")
                .append("    outline: 1px solid;\n")
                .append("    float: left;\n")
                .append("    background-color: Green;\n")
                .append("}\n")
                .append("\n")
                .append(".cellBlue {\n")
                .append("  width: 100px;\n")
                .append("    height: 100px;\n")
                .append("    outline: 1px solid;\n")
                .append("    float: left;\n")
                .append("    background-color: Blue;\n")
                .append("}\n")
                .append("\n")
                .append(".cellYellow {\n")
                .append("  width: 100px;\n")
                .append("    height: 100px;\n")
                .append("    outline: 1px solid;\n")
                .append("    float: left;\n")
                .append("    background-color: Yellow;\n")
                .append("}\n");

        htmlText.append( "</style>\n" );
        htmlText.append( "</head>\n\n" );

        htmlText.append( "<body>\n" );

        htmlText.append("<div>\n" + "    <div class=\"header\"> \n" + "    \n" +
                //First we need to set a header for each game map which each player sees
                "     <p> Player ").append(playerIndex + 1)
                .append("</p>\n")
                .append("     <p> Moves: ").append(direction).append(" </p> \n")
                .append("    </div>\n")
                .append("    \n");

        //Now we will build the current map depending on the players current position
        //We will change colours of new tiles that have been stepped on and mark the player's current position
        //For loop used to loop through each grid
        for (int j = 0; j < map.getMapSizeVar(); j++) {
            for (int i = 0; i < map.getMapSizeVar(); i++) {

                //playerHere is set to false at each iteration
                playerHere = false;

                //Check if the player went on this tile already
                if(ifTileExists(i, j)){

                    //If the tile exists then the player must be on one of these tiles
                    //Checking if the current tile is the players current position on the map
                    if(position.x == i && position.y == j){
                        playerHere = true;
                    }

                    //Obtain the tile type of the current tile
                    tileType = map.getTileType(i,j);
                }

                else{
                    //If not the tile has a default tile type
                    tileType = 3;
                }

                switch(tileType){
                    //Grass tile
                    case 0:

                        if(playerHere){

                            htmlText.append("<div class=\"cellGreen\">" +
                                    "<img src=\"player.png\" alt=\"player\">" +
                                    "</div>\n");
                        }

                        else{

                            htmlText.append("<div class=\"cellGreen\"></div>\n");

                        }
                        break;

                    //Water tile
                    case 1:

                        if(playerHere){

                            htmlText.append("<div class=\"cellBlue\">" +
                                    "<img src=\"player.png\" alt=\"player\">" +
                                    "</div>\n");
                        }

                        else{

                            htmlText.append("<div class=\"cellBlue\"></div>\n");

                        }
                        break;

                    //Treasure Tile
                    case 2:

                        if(playerHere){

                            htmlText.append("<div class=\"cellYellow\">" +
                                    "<img src=\"player.png\" alt=\"player\">" +
                                    "</div>\n");

                        }

                        else{

                            htmlText.append("<div class=\"cellYellow\"></div>\n");

                        }
                        break;

                    default:
                        //No need to check for player here as a player can never be on a gray tile
                        htmlText.append("<div class=\"cellGray\"></div>\n");
                        break;
                }
            }
        }

        htmlText.append("\n</div>");
        htmlText.append( "</body>\n" );
        htmlText.append( "</html>\n" );

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(htmlText.toString());
            bw.close();
        }catch(IOException io){
            io.printStackTrace();
            returnValue = 0;
        }

        return returnValue;
    }
}
