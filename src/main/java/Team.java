import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//This class is a composite class of Player objects
public class Team implements User{

    //Stores all the players of each team
    ArrayList<Player> players = new ArrayList<Player>();

    //Stores the position which all the players in the team have discovered
    ArrayList<Position> positions = new ArrayList<Position>();

    //Constructor for team
    Team(){

    }

    //Adds a player to the team
    void addPlayer(Player player){
        players.add(player);
    }

    //Method used to add a position to the positions ArrayList using the x and y values
    public void addToPositions(int posx, int posy){
        Position position = new Position(posx, posy);
        positions.add(position);
    }

    //Adding a given player position to the teamPositions array list
    void updateTeamPositions(Player player){
        //If a player has not already went over the current tile then add the tile
        if(positionDoesNotExist(player.position)){
            addToPositions(player.position.x, player.position.y);
        }
    }

    //Adding the player positions to the playerPositions array
    void updateAllTeamPositions(){
        //Adding all the positions of the player in the team
        for(Player player: players){
            //If a player has not already went over the current tile then add the tile
            if(positionDoesNotExist(player.position)){
                addToPositions(player.position.x, player.position.y);
            }
        }
    }

    //Method to check if the position exists in the team positions
    boolean positionDoesNotExist(Position position){

        boolean positionDoesNotExist = true;

        //Loop through each player in the team
        for(Position teamPosition: positions){
            //If the position inputted is equal to a position in the team
            if(position.toString().equals(teamPosition.toString())){
                //The check is set to high
                positionDoesNotExist = false;

            }
        }

        return positionDoesNotExist;
    }

    //The current position of each player is added to a tile map within a team class
    //This tile map is updated instead of a player
    //Each player has access to this tile map

    //Method used to change the main html file
    int changeHtmlFile(int playerIndex, Map map, Player player){
        //Value to return to mark if method has run successfully or not
        //Set to 1 by default. This will change to 0 if an error is encountered
        int returnValue = 1;

        String direction = player.getPreviousDirections();

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
                Position position = new Position(i, j);

                //Check if a player in the team went on this tile
                if(!positionDoesNotExist(position)){

                    //If the tile exists then the player must be on one of these tiles
                    //Checking if the current tile is the players current position on the map
                    if(player.position.x == i && player.position.y == j){
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
