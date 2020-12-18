package rainrisk;

public class Waypoint extends Ship {

    public Waypoint() {
        super(new Position(10, 1));
    }

    protected void turn(Action action) {
        int offset = action.getSteps() / 90 % allDirections.size();

        for (int i = 0; i < offset; i++) {
            if (action.isRightTurn()) {
                rotateRight();
            } else if (action.isLeftTurn()) {
                rotateLeft();
            }
        }
    }

    private void rotateRight() {
        position = new Position(position.getY(), -position.getX());
    }

    private void rotateLeft() {
        position = new Position(-position.getY(), position.getX());
    }
}
