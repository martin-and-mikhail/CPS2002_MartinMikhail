import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    int turns = 0;
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
        System.out.println("Welcome to the Treasure Map Game by Martin Bartolo and Mikhail Cassar");
        Game game = new Game();
        game.startGame();
    }


    // Method to initialise map and players and start the main game loop
    private void startGame() {
        playerNum = getPlayerNum();
        mapSize = getMapSize();

        System.out.println("Generating map of size "+mapSize+"x"+mapSize+" for "+playerNum+" players.");

        map.generate(/*MIKHAIL MapSize*/);// Generate map

        for(int i = 0; i < playerNum; i++){

            // MIKHAIL method to get starting position of player (random x and y values)

            Player player = new Player(/*MIKHAIL Put position here*/);
            players.add(player);
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
            if(num > 2){
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
}
