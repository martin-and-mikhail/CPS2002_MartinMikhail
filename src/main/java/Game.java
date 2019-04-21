import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private int turns = 0;// Amount of turns which have been played
    int playerNum;// Amount of players

    ArrayList<Player> players = new ArrayList<Player>();// ArrayList of players

    Map map = new Map();// map object

    final private int minPlayers = 2;
    final private int maxPlayersFirstRange = 4;
    final private int minPlayersSecondRange = 5;
    final private int maxPlayers = 8;

    final private int minMapSizeFirstRange = 5;
    final private int minMapSizeSecondRange = 8;
    final private int maxMapSize = 50;

    private Scanner scanner;// to be used throughout class

    //Constructor for the game object
    Game() {
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Treasure Map Game by Martin Bartolo and Mikhail Cassar");
        Game game = new Game();

        //Run startGame method to initialise players and map
        game.startGame(game);

        boolean foundTreasure = false;// will be set to true when the treasure is found by one of the players

        //An array which holds all the players who found the treasure on a given turn
        //This is just in case more than one player finds it on the same turn
        boolean[] winners = new boolean[game.players.size()];

        //Generating the initial html files here before there are any moves
        //Generating an html file for each player in the game
        for(int i = 0; i < game.players.size(); i++){

            if(game.generateHtmlFile(i, game.map.mapSize, " ") == 0){
                System.err.println("Could not generate HTML files");
            }
        }

        //Main game loop
        while (true) {
            game.turns++;//Increment amount of turns which have been played

            //Get each players desired direction of movement for the current turn
            game.directionsLoop();

            //Generating an html file for each player's current state
            for(int i = 0; i < game.players.size(); i++){
                if(game.generateHtmlFile(i, game.map.mapSize, game.players.get(i).directions.get(game.getLastDirection(i))) == 0){
                    System.err.println("Could not generate HTML files");
                }
            }

            //Go through each player in the game and check if they found the treasure
            //Mark the players who have found the treasure
            int i = 0;
            for(Player player: game.players){

                if(player.foundTreasure){
                    foundTreasure = true;
                    winners[i] = true;
                }
                i++;
            }

            //If the treasure has been found by one of the players
            if (foundTreasure) {

                for(i = 0; i < winners.length; i++){

                    if (winners[i]){
                        System.out.println("Congratualtions player " + (i+1) + ", you have found the treasure in " + game.turns + " turns!");
                    }
                }
                break;
            }
        }
    }

    //Method to initialise map along with players and their starting positions
    private void startGame(Game game) {
        game.playerNum = getPlayerNum();

        map.mapSize = getMapSize();
        map.generate();// Generate map

        //In this loop all the Player objects are created along with their starting position in the map
        for (int i = 0; i < game.playerNum; i++) {

            //A new player object is made
            Player player = new Player();

            //The random position of the player is set to a grass tile
            player.position = player.setStartingPosition(map.getGrassTiles());

            //The created player is added to the ArrayList of players
            players.add(player);
        }
    }

    // Method to get the amount of players from the user
    private int getPlayerNum() {
        int num;
        System.out.println("How many players will be playing? (Pick a number between 2 and 8)");

        while (true) {
            //Get user input
            scanner = new Scanner(System.in);

            //Validate user input
            num = validatePlayerNum(scanner);

            //Return value if it is valid (not an error value)
            if (num > 1) {
                return num;
            }
        }
    }

    // Method to validate the user's input for the player number
    int validatePlayerNum(Scanner scanner) {
        int num;
        try {
            //Set to user input from getPlayerNum
            num = scanner.nextInt();
        }
        //If input is not an integer
        catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number between 2 and 8");
            return 0;//Return error value of 0
        }
        //If input is correct
        if (num >= minPlayers && num <= maxPlayers) {
            return num;//Return value entered by the user
        }
        //If input is not within required range
        else {
            System.err.println("Please enter a number between 2 and 8");
            return 1;//Return error value of 1
        }
    }

    // Method to get the map size from the user
    private int getMapSize() {
        int size;
        System.out.println("How large would you like the map to be? (Map will be n x n)");

        while (true) {
            //Get user input
            scanner = new Scanner(System.in);

            //Validate user input
            size = validateMapSize(scanner);

            //Return value if it is valid (not an error value)
            if (size > 4) {
                return size;
            }
        }
    }

    // Method to validate the user's input for the map size
    int validateMapSize(Scanner scanner) {
        int size;
        try {
            //Set to user input from getMapSize
            size = scanner.nextInt();
        }

        //If input is not an integer
        catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number");
            return 0;//Return error value of 0
        }

        //If input is above largest allowed map size
        if (size > maxMapSize) {
            System.err.println("Map too big. Please enter a size below 50");
            return 1;//Return error value of 1
        }

        //If map is too small for 2-4 players
        else if (playerNum <= maxPlayersFirstRange && size < minMapSizeFirstRange) {
            System.err.println("Map too small. Please enter a size of 5 or more");
            return 2;//Return error value of 2
        }

        //If map is too small for 5-8 players
        else if (playerNum >= minPlayersSecondRange && size < minMapSizeSecondRange) {
            System.err.println("Map too small. Please enter a size of 8 or more");
            return 3;//Return error value of 3
        }

        //If input is correct
        else {
            return size;//Return value entered by the user
        }
    }



    // Method to get direction which each player would like to move in for the current turn
    private void directionsLoop() {
        char direction;//(u, d, l or r) depending on user's desired direction of movement
        boolean validMove;//Condition to break out of while loop when a valid direction is entered

        //Loop through each player in ArrayList
        for (Player player : players) {
            System.out.println("Player " + (players.indexOf(player) + 1) + ", please choose a direction (u, d, l or r).");

            validMove = false;
            while (!validMove) {
                //Get user input
                scanner = new Scanner(System.in);

                //Make sure that user input is valid (i.e. one of u, d, l or r)
                direction = validateDirectionInput(scanner);

                //Check if move is within map and execute if it is
                if (checkOutOfBounds(direction, player, map.mapSize) == 1) {
                    validMove = true;

                    //Change player's position variables to new position
                    player.move(direction);

                    //Triggers event for corresponding tile type
                    map.evaluateCurrentPlayerTile(player);
                }
            }
        }
    }

    // Method to check whether a move is within the map boundaries
    int checkOutOfBounds(char direction, Player player, int mapSize) {
        switch (direction) {
            case 'l':
                //If map limit is exceeded
                if (player.position.x - 1 < 0) {
                    System.err.println("Cannot move left. Please try another direction.");
                    return 0;//Return error value of 0
                }

                //If move is within map
                else {
                    System.out.println("Player moved to the left");
                    return 1;//Return correct value 1 to indicate that move is valid
                }

            case 'r':
                //If map limit is exceeded
                if (player.position.x + 1 >= mapSize) {
                    System.err.println("Cannot move right. Please try another direction.");
                    return 0;//Return error value of 0
                }
                //If move is within map
                else {
                    System.out.println("Player moved to the right");
                    return 1;//Return correct value 1 to indicate that move is valid
                }

            case 'u':
                //If map limit is exceeded
                if (player.position.y - 1 < 0) {
                    System.err.println("Cannot move up. Please try another direction.");
                    return 0;//Return error value of 0
                }
                //If move is within map
                else {
                    System.out.println("Player moved up");
                    return 1;//Return correct value 1 to indicate that move is valid
                }

            case 'd':
                //If map limit is exceeded
                if (player.position.y + 1 >= mapSize) {
                    System.err.println("Cannot move down. Please try another direction.");
                    return 0;//Return error value of 0
                }
                //If move is within map
                else {
                    System.out.println("Player moved down");
                    return 1;//Return correct value 1 to indicate that move is valid
                }

            default:
                return 0;//Return error value of 0
        }
    }

    // Method to check whether an inputted direction is valid (i.e. either u, d, l or r)
    char validateDirectionInput(Scanner scanner) {
        String direction;//User input
        char c;//User input after it being converted into a character
        String directions = "udlr";//String containing each accepted direction

        try {
            //Set to user input from getDirections
            direction = scanner.next();

            //Ensure that inputted string is of length 1
            if (direction.length() > 1) {
                throw new RuntimeException("Input too long. Please enter a character (u, d, l or r)");
            }

            //Convert input string to char
            c = direction.charAt(0);

            //Ensure that character is a letter
            if (!Character.isLetter(c)) {
                throw new RuntimeException("Input is not a character. Please enter a character (u, d, l or r)");
            }
        }

        //If an error is thrown in the try block
        catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return 'y';//Return an error value which we will associate with an exception when testing
        }

        //Change char input to lowercase to allow U, D, L and R
        c = Character.toLowerCase(c);

        //If input is a char but not one of the directions
        if (!directions.contains(Character.toString(c))) {
            System.err.println("Invalid input. Please enter a direction (u, d, l or r)");
            return 'x';//Return an error value which we will associate with an invalid character when testing
        }

        //If input is correct
        else {
            return c;//Return valid user inputted character
        }
    }


    // This method is used to generate the HTML files so that they can be opened in browser
    int generateHtmlFile(int playerIndex, int mapSize, String direction) {
        //Value to return to mark if method has run successfully or not
        //Set to 1 by default. This will change to 0 if an error is encountered
        int returnValue = 1;

        //This variable is used to hold the type of tile which the player has landed on
        int tileType;

        //This variable checks if the player is currently on this tile
        boolean playerHere;

        //A file object is being created where the name is given depending on the number of the player
        File file = new File(" map_player_" + (playerIndex +1)+ ".html");

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
                "    width: ").append(mapSize).append("00px;\n")
                .append("    height: ").append(mapSize + 1).append("00px;\n")
                .append("}\n")
                .append("\n")
                .append(".header {\n").append(
                //The width of the header is changed depending on the size of the map
                "  width: ").append(mapSize).append("00px;\n")
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
        for (int j = 0; j < mapSize; j++) {
            for (int i = 0; i < mapSize; i++) {

                //playerHere is set to false at each iteration
                playerHere = false;

                //Check if the player went on this tile already
                if(players.get(playerIndex).ifTileExists(i, j)){

                    //If the tile exists then the player must be on one of these tiles
                    //Checking if the current tile is the players current position on the map
                    if(players.get(playerIndex).position.x == i && players.get(playerIndex).position.y == j){
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

    // Method to return the last direction which the player has moved
    private int getLastDirection(int playerIndex){
       return players.get(playerIndex).directions.size() - 1;
    }
}

