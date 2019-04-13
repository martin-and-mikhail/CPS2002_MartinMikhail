import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    static int turns = 0;// Amount of turns which have been played
    int playerNum;// Amount of players
    int mapSize;// Size of map n x n

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

    public static void main(String[] args) {
        boolean foundTreasure = false;// will be set to true when the treasure is found by one of the players

        System.out.println("Welcome to the Treasure Map Game by Martin Bartolo and Mikhail Cassar");
        Game game = new Game();
        game.startGame();
<<<<<<< HEAD
    }
    
=======

        while(true){
            turns ++;// incremenet amount of turns which have been played

            //Generate HTML files

            // get each players desired direction of movement for the current turn
            game.directionsLoop();

            // if the treasure has been found by one of the players
            foundTreasure = true;
            if(foundTreasure){
                //Congratulations to Player X, you won the game!!
                break;
            }
        }
    }

    // Method to initialise map and players and start the main game loop
>>>>>>> 0d36120007cbfa66c1713605ad09b968f4877836
    private void startGame() {
        playerNum = getPlayerNum();
        mapSize = getMapSize();    // Method to initialise map and players and start the main game loop

        System.out.println("Generating map of size "+mapSize+"x"+mapSize+" for "+playerNum+" players.");

        map.generate(/*MIKHAIL MapSize*/);// Generate map

        for(int i = 0; i < playerNum; i++){
            // MIKHAIL method to get starting position of player (random x and y values)
            //Player player = new Player(/* MIKHAIL position from starting position method */);
            //players.add(player);
        }
    }

    // Method to get the amount of players from the user
    private int getPlayerNum() {
        int num = 0;
        System.out.println("How many players will be playing? (Pick a number between 2 and 8)");
        while(true) {
            // get user input
            scanner = new Scanner(System.in);
            num = validatePlayerNum(scanner);
            // if value of num is not an error value
            if(num > 1){
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
        catch(InputMismatchException e){
            System.err.println("Invalid input. Please enter a number between 2 and 8");
            return 0;// error value of 0
        }
        // if input is correct
        if (num >= minPlayers && num <= maxPlayers){
            return num;
        }
        // if input is not within required range
        else{
            System.err.println("Please enter a number between 2 and 8");
            return 1;// error value of 1
        }
    }

    // Method to get the map size from the user
    private int getMapSize() {
        int size = 0;
        System.out.println("How large would you like the map to be? (Map will be n x n)");
        while(true) {
            // get user input
            scanner = new Scanner(System.in);
            size = validateMapSize(scanner);
            // if value of num is not an error value
            if(size > 4){
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
        catch(InputMismatchException e){
            System.err.println("Invalid input. Please enter a number");
            return 0;// error value of 0
        }

        // if input is above largest allowed map size
        if(size > maxMapSize){
            System.err.println("Map too big. Please enter a size below 50");
            return 1;// error value of 1
        }

        // if map is too small for 2-4 players
        else if(playerNum <= maxPlayersFirstRange && size < minMapSizeFirstRange){
            System.err.println("Map too small. Please enter a size of 5 or more");
            return 2;// error value of 2
        }

        // if map is too small for 5-8 players
        else if(playerNum >= minPlayersSecondRange && size < minMapSizeSecondRange){
            System.err.println("Map too small. Please enter a size of 8 or more");
            return 3;// error value of 3
        }

        // if input is correct
        else{
            return size;
        }
    }

    // Method to get direction which each player would like to move in for the current turn
    private void directionsLoop() {
        char direction;// (u, d, l or r) depending on user's desired direction of movement
        boolean validMove;// condition to break out of while loop when a valid direction is entered

        // loop through each player in ArrayList
        for(Player player : players) {
            System.out.println("Player " + (players.indexOf(player) + 1) + ", please choose a direction (u, d, l or r).");

            validMove = false;
            while (!validMove) {
                // get user input
                scanner = new Scanner(System.in);
                // make sure that user input is valid (i.e. one of u, d, l or r)
                direction = validateDirectionInput(scanner);

                // check if move is within map and execute if it is
                if (checkOutOfBounds(direction, player) == 1) {
                    validMove = true;
                    player.move(direction);// STILL NEED TO FIX
                }
            }
        }
    }

    // Method to check whether a move is within the map boundaries
    int checkOutOfBounds(char direction, Player player) {
        switch (direction) {
            case 'l':
                // If map limit is exceeded
                if (player.position.x == 0) {
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
                if (player.position.x == mapSize-1) {
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
                if (player.position.y == 0) {
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
                if (player.position.y == mapSize-1) {
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

        try{
            // set to user input from getDirections
            direction = scanner.next();

            // check if length of input is more than 1
            if(direction.length() > 1) {
                throw new RuntimeException("Input too long. Please enter a character (u, d, l or r)");
            }
            // convert input string to char
            c = direction.charAt(0);
            // check if character is a letter
            if (!Character.isLetter(c)){
                throw new RuntimeException("Input is not a character. Please enter a character (u, d, l or r)");
            }
        }

        // if input is not a char
        catch(RuntimeException e){
            System.err.println(e.getMessage());
            return 'y';// return an error value which we will associate with InputMismatchException when testing
        }

        // change char input to lowercase to allow (U, D, L and R)
        c = Character.toLowerCase(c);

        // if input is a char but not one of the directions
        if(!directions.contains(Character.toString(c))){
            System.err.println("Invalid input. Please enter a direction (u, d, l or r)");
            return 'x';// return an error value which we will associate with an invalid character when testing
        }

        // if input is correct
        else{
            return c;
        }
    }
}
