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

    //Stores the positions of each player at each turn
    ArrayList<Position[]> teamPositions = new ArrayList<Position[]>();

    //Stores the current position of each player in the team
    Position[] playerPositions = new Position[players.size()];

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

    //Adding the player positions to the playerPositions array
    void gettingCurrentPositonOfEachPlayer(){
        for(int i = 0; i < players.size(); i++){
            playerPositions[i] = players.get(i).getLatPosition();
        }
    }

    //The current player positions are added to the total team positions
    void updateTeamPositions(){
        teamPositions.add(playerPositions);
    }

    //The current position of each player is added to a tile map within a team class
    //This tile map is updated instead of a player
    //Each player has access to this tile map

}
