package rainrisk;

public class Position {
    int y;
    int x;

    public Position(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public void moveEast(int x) {
        this.x += x;
    }

    public void moveWest(int x) {
        this.x -= x;
    }

    public void moveNorth(int y) {
        this.y += y;
    }

    public void moveSouth(int y) {
        this.y -= y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
