import java.util.ArrayList;

//This class is a composite class of Player objects
public class Team {

    //Stores all the players of each team
    ArrayList<Player> players = new ArrayList<Player>();// ArrayList of players

    //Stores the positions of each player at each turn
    //This can be done by maybe storing the first five elements of each player at each turn
    //In an array list of position array size playerNum
    //Each player needs to have access to this so that these tiles can become available

    //create a tile map here and update it
    //each player has access to this tile map

    //Stores the position which all the players occupy
    //This array list is updated between turns
    ArrayList<Position> teamPositions = new ArrayList<Position>();

    //Constructor for team
    Team(){

    }

    //Adds a player to the team
    void addPlayer(Player player){
        players.add(player);
    }

    //A remove player function is not made since there is no need to remove a player

    //Gets a specific player from the team
    Player getPlayer(int index){
        return players.get(index);
    }

    //Adding a given player position to the teamPositions array list
    void updateTeamPositions(Player player){

        //If a player has not already went over the current tile then add the tile
        if(!positionExists(player.position)){
            teamPositions.add(player.position);
        }

    }


    //Adding the player positions to the playerPositions array
    void updateAllTeamPositions(){

        //Adding all the positions of the player in the team
        for(Player player: players){

            //If a player has not already went over the current tile then add the tile
            if(!positionExists(player.position)){
                teamPositions.add(player.position);
            }
        }
    }

    //Method to check if the position exists in the team positions
    boolean positionExists(Position position){

        boolean positionExists = false;

        //Loop through each player in the team
        for(Position teamPosition: teamPositions){

            //If the position inputted is equal to a position in the team
            if(position == teamPosition){
                //The check is set to high
                positionExists = true;
            }
        }

        return positionExists;
    }

    //The current position of each player is added to a tile map within a team class
    //This tile map is updated instead of a player
    //Each player has access to this tile map

}
