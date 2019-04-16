public class Position {
    int x;
    int y;

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    Position(int px, int py) {
        x = px;
        y = py;
    }
}
