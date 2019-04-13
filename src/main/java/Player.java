public class Player {

    Position position;

    public Player(Position pPosition){
        position = pPosition;
    }

    //Need to obtain size of mapSize
    int mapSize;

    void setMapSize(int mapSize){
        this.mapSize = mapSize;
    }


    //The player moves the next tile given the direction he inputs
    void move(char direction){

        //A switch statement is used to represent all possible directions
        switch(direction){
            case 'l':
                //Check if map limit going left is reached first
                //This is when x=0

                if(position.x == 0){
                    //Output to the user that the map limit has been reached
                }

                else{
                    //The x coordinate is decremented so that it looks like the player moved on tile back
                    position.x = position.x - 1;
                }

                break;

            case 'r':

                if(position.x == mapSize){

                }

                else{
                    position.x = position.x + 1;
                }
                break;

            case 'u':

                if(position.y == 0){
                    //Output to the user that the map limit has been reached
                }

                else{
                    //The x coordinate is decremented so that it looks like the player moved on tile back
                    position.y = position.y - 1;
                }

                break;

            case 'd':

                if(position.y == mapSize){
                    //Output to the user that the map limit has been reached
                }

                else{
                    //The x coordinate is decremented so that it looks like the player moved on tile back
                    position.y = position.y + 1;
                }
                break;

            default:
                //error handling here
                break;
        }

    }

    boolean setPosition(Position p){


        return true;
    }

}
