import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private int turns = 0;// Amount of turns which have been played
    int teamNum;// Amount of teams
    int playerNum;// Amount of players

    ArrayList<Team> teams = new ArrayList<Team>();// ArrayList for teams
    ArrayList<Player> players = new ArrayList<Player>();// ArrayList of players

    // map object
    Map map;

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

        //The position array lists of each team are initialised with the starting position of the corresponding players
        for(Team team: game.teams){
            team.updateAllTeamPositions();
        }

        //Main game loop
        while (true) {
            game.turns++;//Increment amount of turns which have been played

            System.out.println("------------------------------------------------------------------------\n");

            //Get each players desired direction of movement for the current turn
            game.directionsLoop();

            //After the main directions loop and each player has moved the map is updated

            //Go through each player in the game and check if they found the treasure
            //Mark the players who have found the treasure
            int i = 0;

            for (Player player : game.players) {

                if (player.foundTreasure) {

                    foundTreasure = true;
                    winners[i] = true;

//                    //Check which team contains the player
//                    for(Team team : game.teams){
//                        //If the player is in the team
//                        if(team.players.indexOf(player) >= 0){
//                            //The html file is changed to the last player which won
//                            team.changeHtmlFile(team.players.indexOf(player), game.map , player);
//                        }
//                    }

                }
                i++;
            }

            //If the treasure has been found by one of the players
            if (foundTreasure) {

                for (i = 0; i < winners.length; i++) {

                    if (winners[i]) {

                        System.out.println("Congratualtions player " + (i + 1) + ", you have found the treasure in " + game.turns + " turns!");
                    }
                }

                break;
            }
        }

        System.out.println("------------------------------------------------------------------------\n");

        //After a player wins the game the user is able to prompted to exit the game
        game.exitGame(game);
    }

    //Method to initialise map along with players and their starting positions
    private void startGame(Game game) {

        //get number of players from user
        game.playerNum = getPlayerNum();

        //get number of teams from user
        game.teamNum = getTeamNum();

        //The remainder of the total number of players divided by the total number of teams is obtained
        int extraPlayersNum = playerNum % teamNum;

        //The total number of player per team is obtained excluding the extra players
        int playersInTeamNum = (playerNum - extraPlayersNum)/teamNum;

        //get map size from user
        int mapSize = getMapSize();

        //get map type from user
        String type = getMapType();

        //create instance of map creator
        MapCreator creator = new MapCreator();

        //create map of chosen type and size using the creator classes
        map = creator.createMap(type, mapSize);

        //In this loop all the Player objects are created along with their starting position in the map
        for (int i = 0; i < game.playerNum; i++) {

            //A new player object is made
            Player player = new Player();

            //The random position of the player is set to a grass tile
            player.position = player.setStartingPosition(map.getGrassTiles());

            //The created player is added to the ArrayList of players
            players.add(player);
        }

        //Holds the players which have been added to a team
        ArrayList<Player> addedPlayers = new ArrayList<Player>();

        //Now to assign all the player objects to a random team
        for (int i = 0; i < game.teamNum; i++) {

            //A new team is created
            Team team;

            //Generate the team
            team = generateTeam(addedPlayers, playersInTeamNum);

            //Add the team to the list of teams in the game
            teams.add(team);
        }

        //If the players are not all evenly distributed among the teams
        if(extraPlayersNum != 0){
            distributeRemainder(addedPlayers, extraPlayersNum);
        }

        //List teams and their players
        int i = 1;
        for(Player player: players){
            System.out.println("Player " + i + " is in Team "+ (getTeamIndex(player)+1));
            i++;
        }
    }

    // Method to get the amount of teams from the user
    private int getTeamNum() {
        int num;
        System.out.println("How many teams should the players be split into? (Pick a number between 2 and the amount of players in the game)");

        while (true) {
            //Get user input
            scanner = new Scanner(System.in);

            //Validate user input
            num = validateTeamNum(scanner);

            //Return value if it is valid (not an error value)
            if (num > 1) {
                return num;
            }
        }

    }

    // Method to validate the user's input for the team number
    int validateTeamNum(Scanner scanner) {

        int num;
        try {
            //Set to user input from getPlayerNum
            num = scanner.nextInt();
        }
        //If input is not an integer
        catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter a number between 1 and the amount of players in the game");
            return 0;//Return error value of 0
        }
        //If input is correct
        if (num > 1 && num <= playerNum) {
            return num;//Return value entered by the user
        }
        //If input is not within required range
        else {
            System.err.println("Please enter a number between 1 and the amount of players in the game");
            return 1;//Return error value of 1
        }
    }

    //Method to generate the teams
    Team generateTeam(ArrayList<Player> addedPlayers, int playersInTeamNum){

        Random random = new Random();

        //Check if the current player exists in a team
        boolean playerIsInATeam;

        //Holds a random index of a player
        int rand;

        //A new team is created
        Team team = new Team();

            //Randomly add the specified amount of players to a team
            //Get playerInTeamNum random players from the players array list
            for (int j = 0; j < playersInTeamNum; j++) {

                //At each iteration this check is always initialised to true
                //If there is no matching value in the array list then the while loop breaks
                playerIsInATeam = false;

                //Keep on looping until a new player index is obtained
                do {
                    //A random index is obtained
                    rand = random.nextInt(playerNum);

                    //If no player has currently been added to a team
                    if(addedPlayers.size() == 0){

                        //A random index is obtained
                        rand = random.nextInt(playerNum);

                        //An initial player has been added
                        team.addPlayer(players.get(rand));

                        addedPlayers.add(players.get(rand));

                        //If the size of a team is only one player then this team is full
                        //If not then continue adding players until the maximum is reached
                        if(playersInTeamNum== 1){
                            playerIsInATeam = false;
                        }
                    }

                    //If a player has been added to a team
                    else {

                        //Initialised to false since we are checking if the current player is already in a team
                        playerIsInATeam = false;

                        //Loops through all the players which are already in a team
                        //Ends when a player which is not in a team is obtained
                        for (Player player : addedPlayers) {

                            //If the current player has already been generated
                            if (players.get(rand) == player) {
                                playerIsInATeam = true;
                            }
                        }

                        if(!playerIsInATeam){
                            //Add the player to the team
                            team.addPlayer(players.get(rand));

                            addedPlayers.add(players.get(rand));

                            //The check is false and the loop is broken
                            break;
                        }
                    }

                    //Keep on looping while the current player being obtained is already in a team
                    } while (playerIsInATeam);
            }

        //Returns a team with the player
        return team;
    }

    // Method to distribute the remaining player if the players are not evenly distrubted among the teams
    void distributeRemainder(ArrayList<Player> addedPlayers, int extraPlayersNum){

        Random random = new Random();

        //Array list which holds the teams which have already been generated
        ArrayList<Team> obtainedTeams = new ArrayList<Team>();

        //Check if the current player exists in a team
        boolean playerIsInATeam;

        //check if an extra player is already added to the team
        boolean teamIsFull;

        //Holds a random index for the player and the team respectively
        int playerIndex;
        int teamIndex;

        //Obtain a player which is not in a team for extraPlayerNum times
        for(int i = 0; i < extraPlayersNum; i++){

            //First obtain a random unique team
            //This is so only one extra player is added to a team so there would not be much of a disadvantage to the other players

            //Keep on looping until a new team index is obtained
            do {

                teamIsFull = false;

                //A random index is obtained
                teamIndex = random.nextInt(teamNum);

                //This is used so as to set up the obtainedTeams array list
                if(obtainedTeams.size() == 0){

                    obtainedTeams.add(teams.get(teamIndex));

                }

                else{

                    for (Team team : obtainedTeams) {

                        //If the current player has already been generated
                        if (teams.get(teamIndex) == team) {
                            teamIsFull = true;
                        }
                    }

                }

            }while(teamIsFull);

            //Now a new team is obtained with every iteration

            //Keep on looping until a new player index is obtained
            do {
                //At each iteration the checks are set to false
                playerIsInATeam = false;

                //A random index is obtained
                playerIndex = random.nextInt(playerNum);

                for (Player player : addedPlayers) {

                    //If the current player has already been generated
                    if (players.get(playerIndex) == player) {
                        playerIsInATeam = true;
                    }
                }

                if(!playerIsInATeam){
                    //Add the player to the team
                    teams.get(teamIndex).addPlayer(players.get(playerIndex));

                    addedPlayers.add(players.get(playerIndex));

                    //The check is set to false and the loop is broken
                    break;
                }


            }while(playerIsInATeam);

        }
    }

    // Method to get the map type from the user
    private String getMapType() {
        int num;

        System.out.println("Would you like to play in\n 1) a safe map with 10% water squares\n 2) a hazardous map with 25%-35% water squares");

        while(true){
            //Get user input
            scanner = new Scanner(System.in);

            //Validate user input
            num = validateMapType(scanner);

            if(num == 1){
                return "safe";
            }
            else if(num == 2){
                return "hazardous";
            }
        }
    }

    // Method to validate the user's input for the map type
    int validateMapType(Scanner scanner) {
        int num;

        try {
            //Set to user input from getMapType
            num = scanner.nextInt();
        }
        //If input is not an integer
        catch (InputMismatchException e) {
            System.err.println("Invalid input. Please enter 1 or 2");
            return 0;//Return error value of 0
        }
        //If input is correct
        if (num == 1 || num == 2) {
            return num;//Return value entered by the user
        }
        //If input is not within required range
        else {
            System.err.println("Please enter 1 or 2");
            return 3;//Return error value of 1
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
        boolean foundTreasure = false;//Condition to check if a player has found the treasure

        //Loop through each player in ArrayList
        for (Player player : players) {

            if(!foundTreasure) {
                //At the end of the current player's turn the main html file is changed
                teams.get(getTeamIndex(player)).changeHtmlFile(players.indexOf(player), map, player);
            }

            System.out.println("Player " + (players.indexOf(player) + 1) + ", please choose a direction (u, d, l or r).");

            validMove = false;
            while (!validMove) {
                direction = 'x';
                //Make sure that user input is valid (i.e. one of u, d, l or r)
                while(direction == 'x' || direction == 'y') {
                    scanner = new Scanner(System.in);
                    direction = validateDirectionInput(scanner);
                }

                //Check if move is within map and execute if it is
                if (checkOutOfBounds(direction, player, map.getMapSizeVar()) == 1) {
                    validMove = true;

                    //Change player's position variables to new position
                    player.move(direction);

                    //Adding the new direction to the corresponding team
                    teams.get(getTeamIndex(player)).updateTeamPositions(player);

                    //Triggers event for corresponding tile type
                    map.evaluateCurrentPlayerTile(player);

                    if(player.foundTreasure){
                        foundTreasure = true;
                    }
                }
            }
        }
    }

    //Gets the index of the team the current player is in
    int getTeamIndex(Player player){
        for(Team team: teams){
            for(Player usePlayer: team.players){
                if(player == usePlayer){
                    return teams.indexOf(team);
                }
            }
        }
        return -1;
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
                    System.out.println("Player moved to the left\n");
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
                    System.out.println("Player moved to the right\n");
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
                    System.out.println("Player moved up\n");
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
                    System.out.println("Player moved down\n");
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

    //Method to delete Html files
    void deleteHtmlFile(){

        //Deleting the only file used in the game
        try{
            File file = new File("map.html");

            if(!file.delete()) {
                System.out.println("File does not exist");
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //Method to exit the game
    private void exitGame(Game game){
        if(getExitChar() == 'e'){
            //Delete all Html Files
            deleteHtmlFile();
            System.out.println("Thank you for playing!");
        }
    }

    //Method to get the user to input e to exit the program
    private char getExitChar(){
        char exit = 'x';

        System.out.println("Press e if you would like to exit the program");

        //Make sure that user input is valid
        while(exit == 'x' || exit == 'y') {
            scanner = new Scanner(System.in);
            exit = validateExitChar(scanner);
        }

        return exit;
    }

    //Method to validate user input from getExitChar()
    char validateExitChar(Scanner scanner){
        String input;//User input
        char c;//User input after it being converted into a character

        try {
            //Set to user input from getDirections
            input = scanner.next();

            //Ensure that inputted string is of length 1
            if (input.length() > 1) {
                throw new RuntimeException("Input too long. Please enter e to exit");
            }

            //Convert input string to char
            c = input.charAt(0);

            //Ensure that character is a letter
            if (!Character.isLetter(c)) {
                throw new RuntimeException("Input is not a character. Please enter e to exit");
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
        if (c != 'e') {
            System.err.println("Invalid input. Please enter e to exit");
            return 'x';//Return an error value which we will associate with an invalid character when testing
        }

        //If input is correct
        else {
            return c;//Return valid user inputted character
        }
    }
}

