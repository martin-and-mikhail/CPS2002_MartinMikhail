import java.util.ArrayList;

public interface User {
    //The User interface is the component of the composite design pattern

    ArrayList<Position> positions = new ArrayList<Position>();

    //Method used to add a position to the positions ArrayList using the x and y values
    void addToPositions(int posx, int posy);
}
