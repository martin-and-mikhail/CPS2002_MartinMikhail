
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Game {
    static int turns = 0;// Amount of turns which have been played
    int playerNum;// Amount of players

    private static ArrayList<Player> players = new ArrayList<Player>();// ArrayList of players
    private Map map = new Map();// map object

    final private int minPlayers = 2;
    final private int maxPlayersFirstRange = 4;
    final private int minPlayersSecondRange = 5;
    final private int maxPlayers = 8;

    final private int minMapSizeFirstRange = 5;
    final private int minMapSizeSecondRange = 8;
    final private int maxMapSize = 50;

    private Scanner scanner;// to be used throughout class

    public static void main(String[] args) {
        boolean foundTreasure = false;// will be set to true when the treasure is found by one of the players

        System.out.println("Welcome to the Treasure Map Game by Martin Bartolo and Mikhail Cassar");
        Game game = new Game();
        game.startGame();

        //This is the game loop
        while (true) {
            turns++;// incremenet amount of turns which have been played

            //Generating an html file for each player in the game
            for(int i = 0; i < game.players.size(); i++){

                game.generateHtmlFile(i, 5);

            }

            // get each players desired direction of movement for the current turn
            game.directionsLoop();

            // if the treasure has been found by one of the players
            foundTreasure = true;
            if (foundTreasure) {
                //Congratulations to Player X, you won the game!!
                break;
            }
        }
    }

    // Method to initialise map and players and start the main game loop
    private void startGame() {
        playerNum = getPlayerNum();
        int mapSize = getMapSize();    // Method to initialise map and players and start the main game loop

        System.out.println("Generating map of size " + mapSize + "x" + mapSize + " for " + playerNum + " players.");

        //The map is generated with the tile type randomly allocated
        map.mapSize = mapSize;
        map.generate();// Generate map

        //In this loop all the Player objects are created along with their starting position in the map
        for (int i = 0; i < playerNum; i++) {

            //A new player object is made
            Player player = new Player();

            //The random position of the player is set in a grass tile
            //The starting position obtained is also put into the player position array list
            player.position = player.setStartingPosition(map.getGrassTiles());

            players.add(player);
        }
    }

    // Method to get the amount of players from the user
    private int getPlayerNum() {
        int num = 0;
        System.out.println("How many players will be playing? (Pick a number between 2 and 8)");
        while (true) {
            // get user input
            scanner = new Scanner(System.in);
            num = validatePlayerNum(scanner);
            // if value of num is not an error value
            if (num > 1) {
                return num;
            }
        }
    }

    // Method to validate the user's input for the player number
    int validatePlayerNum(Scanner scanner) {
        int num = 0;
        try {
            // set to user input from getPlayerNum
            num = scanner.nextInt();
        }
        // if input is not an int
        catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number between 2 and 8");
            return 0;// error value of 0
        }
        // if input is correct
        if (num >= minPlayers && num <= maxPlayers) {
            return num;
        }
        // if input is not within required range
        else {
            System.err.println("Please enter a number between 2 and 8");
            return 1;// error value of 1
        }
    }

    // Method to get the map size from the user
    private int getMapSize() {
        int size = 0;
        System.out.println("How large would you like the map to be? (Map will be n x n)");
        while (true) {
            // get user input
            scanner = new Scanner(System.in);
            size = validateMapSize(scanner);
            // if value of num is not an error value
            if (size > 4) {
                return size;
            }
        }
    }

    // Method to validate the user's input for the map size
    int validateMapSize(Scanner scanner) {
        int size = 0;
        try {
            // set to user input from getMapSize
            size = scanner.nextInt();
        }

        // if input is not an int
        catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number");
            return 0;// error value of 0
        }

        // if input is above largest allowed map size
        if (size > maxMapSize) {
            System.err.println("Map too big. Please enter a size below 50");
            return 1;// error value of 1
        }

        // if map is too small for 2-4 players
        else if (playerNum <= maxPlayersFirstRange && size < minMapSizeFirstRange) {
            System.err.println("Map too small. Please enter a size of 5 or more");
            return 2;// error value of 2
        }

        // if map is too small for 5-8 players
        else if (playerNum >= minPlayersSecondRange && size < minMapSizeSecondRange) {
            System.err.println("Map too small. Please enter a size of 8 or more");
            return 3;// error value of 3
        }

        // if input is correct
        else {
            return size;
        }
    }

    // Method to get direction which each player would like to move in for the current turn
    private void directionsLoop() {
        char direction;// (u, d, l or r) depending on user's desired direction of movement
        boolean validMove;// condition to break out of while loop when a valid direction is entered

        // loop through each player in ArrayList
        for (Player player : players) {
            System.out.println("Player " + (players.indexOf(player) + 1) + ", please choose a direction (u, d, l or r).");

            validMove = false;
            while (!validMove) {
                // get user input
                scanner = new Scanner(System.in);
                // make sure that user input is valid (i.e. one of u, d, l or r)
                direction = validateDirectionInput(scanner);

                // check if move is within map and execute if it is
                if (checkOutOfBounds(direction, player, map.mapSize) == 1) {
                    validMove = true;

                    //Show position method used to see how player moves
                    System.out.println(player.position.toString());
                    player.move(direction);

                    //Add method here which compares the tile to the current player position and change his tile accordingly
                    //add methods for water event
                    //add method for treasure event

                }
            }
        }
    }

    // Method to check whether a move is within the map boundaries
    int checkOutOfBounds(char direction, Player player, int mapSize) {
        switch (direction) {
            case 'l':
                // If map limit is exceeded
                if (player.position.x - 1 < 0) {
                    System.err.println("Cannot move left. Please try another direction.");
                    return 0;// return error code
                }
                // If move is within map
                else {
                    System.out.println("Player moved to the left");
                    return 1;// return correct code
                }

            case 'r':
                // If map limit is exceeded
                if (player.position.x + 1 >= mapSize) {
                    System.err.println("Cannot move right. Please try another direction.");
                    return 0;// return error code
                }
                // If move is within map
                else {
                    System.out.println("Player moved to the right");
                    return 1;// return correct code
                }

            case 'u':
                // If map limit is exceeded
                if (player.position.y - 1 < 0) {
                    System.err.println("Cannot move up. Please try another direction.");
                    return 0;// return error code
                }
                // If move is within map
                else {
                    System.out.println("Player moved up");
                    return 1;// return correct code
                }

            case 'd':
                // If map limit is exceeded
                if (player.position.y + 1 >= mapSize) {
                    System.err.println("Cannot move down. Please try another direction.");
                    return 0;// return error code
                }
                // If move is within map
                else {
                    System.out.println("Player moved down");
                    return 1;// return correct code
                }

            default:
                return 0;// return error code
        }
    }

    // Method to check whether an inputted direction is valid (i.e. either u, d, l or r)
    char validateDirectionInput(Scanner scanner) {
        String direction;// user input
        char c;// user input after it being converted into a character
        String directions = "udlr";// string containing each accepted direction

        try {
            // set to user input from getDirections
            direction = scanner.next();

            // check if length of input is more than 1
            if (direction.length() > 1) {
                throw new RuntimeException("Input too long. Please enter a character (u, d, l or r)");
            }
            // convert input string to char
            c = direction.charAt(0);
            // check if character is a letter
            if (!Character.isLetter(c)) {
                throw new RuntimeException("Input is not a character. Please enter a character (u, d, l or r)");
            }
        }

        // if input is not a char
        catch (RuntimeException e) {
            System.err.println(e.getMessage());
            return 'y';// return an error value which we will associate with InputMismatchException when testing
        }

        // change char input to lowercase to allow (U, D, L and R)
        c = Character.toLowerCase(c);

        // if input is a char but not one of the directions
        if (!directions.contains(Character.toString(c))) {
            System.err.println("Invalid input. Please enter a direction (u, d, l or r)");
            return 'x';// return an error value which we will associate with an invalid character when testing
        }

        // if input is correct
        else {
            return c;
        }
    }

    void grassTileEvent(Player player) {

    }


    //This method is used to generate the HTML files so that they can be opened in browser
    void generateHtmlFile(int playerIndex, int mapSize) {

        //This variable is used to hols the type of tile which the player has went on
        int tileType;

        //Direction can be used when changing allowign the user to make a move before generating the html code
        //Will work on this tomorrow

        //This variable holds the amount of directions the player made
        //int directionSize = players.get(playerIndex).directions.size();

        //This variable holds the last direction the player went
        //String direction = players.get(playerIndex).directions.get(directionSize);

        System.out.println("File for player " + (playerIndex + 1) + " is being created\n");

        //A file object is being created where the name is given depending on the number of the player
        File file = new File(" map_player_" + (playerIndex +1)+ ".html");

        //The actual file is created here
        try {
            if(file.createNewFile()) {
            }
        } catch (IOException e) {
            e.printStackTrace();
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
                "    width: " + mapSize + "00px;\n" +
                "    height: " + (mapSize + 1) + "00px;\n" +
                "}\n" +
                "\n" +
                ".header {\n" +
                //The width of the header is changed depending on the size of the map
                "  width: " + mapSize + "00px;\n" +
                "  height: 100px;\n" +
                "  outline: 1px solid;\n" +
                "  float: left;\n" +
                "  text-align: center;\n" +
                "  background-color: #1f599a;\n" +
                "  font-family: Arial, sans-serif;\n" +
                "  font-size: 20px;\n" +
                "  color: white;\n" +
                "}\n" +
                "\n" +
                ".cellGray {\n" +
                "    width: 100px;\n" +
                "    height: 100px;\n" +
                "    outline: 1px solid;\n" +
                "    float: left;\n" +
                "    background-color: Gray;\n" +
                "}\n" +
                "\n" +
                ".cellGreen {\n" +
                "  width: 100px;\n" +
                "    height: 100px;\n" +
                "    outline: 1px solid;\n" +
                "    float: left;\n" +
                "    background-color: Green;\n" +
                "}\n" +
                "\n" +
                ".cellBlue {\n" +
                "  width: 100px;\n" +
                "    height: 100px;\n" +
                "    outline: 1px solid;\n" +
                "    float: left;\n" +
                "    background-color: Blue;\n" +
                "}\n" +
                "\n" +
                ".cellYellow {\n" +
                "  width: 100px;\n" +
                "    height: 100px;\n" +
                "    outline: 1px solid;\n" +
                "    float: left;\n" +
                "    background-color: Yellow;\n" +
                "}\n" );

        htmlText.append( "</style>\n" );
        htmlText.append( "</head>\n\n" );

        htmlText.append( "<body>\n" );

        htmlText.append( "<div>\n" +
                "    <div class=\"header\"> \n" +
                "    \n" +
                //First we need to set a header for each game map which each player sees
                "     <p> Player " + (playerIndex + 1) +"</p>\n" +
                "     <p> Moves: </p> \n" +//DIRECTION GOES HERE Dir1 Dir2
                "    </div>\n" +
                "    \n" );

                //Then given the size of the map the number of grids is made
        tileType = 0;

        //For loop used to loop through each grid
        for (int x = 0; x < mapSize; x++) {
            for (int y = 0; y < mapSize; y++) {

                //Check if the player went on this tile already
                if(players.get(playerIndex).ifTileExists(x, y)){

                    //Obtain the tile type of the current tile
                    tileType = map.getTileType(x,y);
                    System.out.println("Tile type is " +  tileType);
                }

                else{
                    //If not the tile has a default tile type
                    tileType = 3;
                }

                switch(tileType){
                    case 0:

                        htmlText.append("<div class=\"cellGreen\"></div>\n");

                        break;

                    case 1:

                        htmlText.append("<div class=\"cellBlue\"></div>\n");

                        break;

                    case 2:

                        htmlText.append("<div class=\"cellYellow\"></div>\n");

                        break;

                    default:

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
        }
        System.out.println("Wrote to file");

    }
}
