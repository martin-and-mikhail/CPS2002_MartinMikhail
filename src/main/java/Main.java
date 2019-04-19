class Main{

    public static void main(String[] args) {
        System.out.println("Welcome to the Treasure Map Game by Martin Bartolo and Mikhail Cassar");
        Game game = new Game();
        game.startGame();

        boolean foundTreasure = false;// will be set to true when the treasure is found by one of the players
        //An array which holds all the players who found the treasure on a given turn
        //This is just in case more than one player finds it on the same turn
        boolean winners[] = new boolean[game.players.size()];

        //Generating the initial html files here before there are any moves
        //Generating an html file for each player in the game
        for(int i = 0; i < game.players.size(); i++){

            game.generateHtmlFile(i, game.map.mapSize, " ");

        }

        System.out.println("Your game files have been generated");

        //This is the game loop
        while (true) {
            game.turns++;// incremenet amount of turns which have been played

            // get each players desired direction of movement for the current turn
            game.directionsLoop();

            //Generating an html file for each player in the game
            for(int i = 0; i < game.players.size(); i++){

                game.generateHtmlFile(i, game.map.mapSize, game.players.get(i).directions.get(game.getLastDirectionsElement(i)));
                System.out.println(game.players.get(i).positions);
            }

            //Go through each player in the game and check if they found the treasure
            //Mark the players who have found the treasure
            int i = 0;
            for(Player player: game.players){

                if(player.getFoundTreasure()){
                    foundTreasure = true;
                    winners[i] = true;
                }

                i++;
            }

            //Method to check if any player has found the treasure

            // if the treasure has been found by one of the players
            //foundTreasure = true;
            if (foundTreasure) {

                for(i = 0; i < winners.length; i++){

                    if (winners[i]){
                        System.out.println("Congratualtions player " + i + 1 + ", you have found the treasure!");
                    }
                }
                //Congratulations to Player X, you won the game!!
                break;
            }
        }
    }

}