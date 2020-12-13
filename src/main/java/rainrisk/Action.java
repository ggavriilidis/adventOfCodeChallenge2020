package rainrisk;

import java.util.Optional;

public class Action {

    private String action;

    private int steps;

    public Action(String action, int steps) {
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
        return "F".endsWith(action);
    }

    public boolean isMoveEast() {
        return "E".endsWith(action);
    }

    public boolean isMoveWest() {
        return "W".endsWith(action);
    }

    public boolean isMoveNorth() {
        return "N".endsWith(action);
    }

    public boolean isMoveSouth() {
        return "S".endsWith(action);
    }

    public int getSteps() {
        return this.steps;
    }
}
