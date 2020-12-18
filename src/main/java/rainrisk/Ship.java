package rainrisk;

import java.util.Arrays;
import java.util.List;

import static rainrisk.Direction.*;

public class Ship {

    protected static final List<Direction> allDirections = Arrays.asList(N, E, S, W);
    private Direction direction = E;
    protected Position position;

    protected Ship() {
        this.position = new Position(0, 0);
    }

    public Ship(Position position) {
        this.position = position;
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

    protected void moveForward(Action action) {
        if (N == direction) {
            position.moveNorth(action.getSteps());
        } else if (S == direction) {
            position.moveSouth(action.getSteps());
        } else if (E == direction) {
            position.moveEast(action.getSteps());
        } else if (W == direction) {
            position.moveWest(action.getSteps());
        }
    }

    protected void turn(Action action) {
        int initialIndex = allDirections.indexOf(direction);
        int finalIndex = 0;
        int numberOfDirections = allDirections.size();
        int offset = action.getSteps() / 90 % numberOfDirections;
        if (action.isRightTurn()) {
            finalIndex = initialIndex + offset;
            if (finalIndex > 3) {
                finalIndex -= numberOfDirections;
            }
        } else if (action.isLeftTurn()) {
            finalIndex = initialIndex - offset;
            if (finalIndex < 0) {
                finalIndex += numberOfDirections;
            }
        }
        direction = allDirections.get(finalIndex);
    }
}
