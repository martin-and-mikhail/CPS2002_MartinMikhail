public class Position {

    //Value of each coordinate
    int x;
    int y;

    //Method to display a position object
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    //Constructor for the position object
    Position(){
    }

    //Constructor for the position object when both x and y values are given
    Position(int px, int py) {
        x = px;
        y = py;
    }
}
