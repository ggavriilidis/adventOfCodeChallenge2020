package rainrisk;

import java.util.*;
import java.util.Arrays;

import static rainrisk.Direction.*;

public class Ship {

    private static final List<Direction> allDirections = Arrays.asList(N, E, S, W);
    private Direction direction;
    private Position position;

    public Ship() {
        this.direction = E;
        this.position = new Position(0, 0);
    }

    public void move(Action action) {
        if (action.isTurning()) {
            turn(action);
        } else if (action.isMoveForward()) {
            moveForward(action);
        } else if (action.isMoveNorth()) {
            position.moveNorth(action.getSteps());
        } else if (action.isMoveSouth()) {
            position.moveSouth(action.getSteps());
        } else if (action.isMoveEast()) {
            position.moveEast(action.getSteps());
        } else if (action.isMoveWest()) {
            position.moveWest(action.getSteps());
        }
    }

    public Position getPosition() {
        return position;
    }

    private void moveForward(Action action) {
        if (direction == N) {
            position.moveNorth(action.getSteps());
        } else if (direction == S) {
            position.moveSouth(action.getSteps());
        } else if (direction == E) {
            position.moveEast(action.getSteps());
        } else if (direction == W) {
            position.moveWest(action.getSteps());
        }
    }

    private void turn(Action action) {
        int initialIndex = allDirections.indexOf(direction);
        int finalIndex = 0;
        int offset = action.getSteps() / 90 % allDirections.size();
        if (action.isRightTurn()) {
            finalIndex = initialIndex + offset;
            if (finalIndex > 3) {
                finalIndex -= 4;
            }
        } else if (action.isLeftTurn()) {
            finalIndex = initialIndex - offset;
            if (finalIndex < 0) {
                finalIndex += 4;
            }
        }
        direction = allDirections.get(finalIndex);
    }
}
