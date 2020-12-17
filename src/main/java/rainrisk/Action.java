package rainrisk;

public class Action {

    private String action;

    private int steps;

    Action(String action, int steps) {
        this.action = action;
        this.steps = steps;
    }

    public boolean isTurning() {
        return isLeftTurn() || isRightTurn();
    }

    public boolean isRightTurn() {
        return "R".equals(action);
    }

    public boolean isLeftTurn() {
        return "L".equals(action);
    }

    public boolean isMoveForward() {
        return "F".equals(action);
    }

    public boolean isMoveEast() {
        return "E".equals(action);
    }

    public boolean isMoveWest() {
        return "W".equals(action);
    }

    public boolean isMoveNorth() {
        return "N".equals(action);
    }

    public boolean isMoveSouth() {
        return "S".equals(action);
    }

    public int getSteps() {
        return this.steps;
    }
}
