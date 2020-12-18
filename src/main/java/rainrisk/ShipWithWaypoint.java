package rainrisk;

import static rainrisk.Direction.*;
import static rainrisk.Direction.W;

public class ShipWithWaypoint extends Ship {

    private Waypoint waypoint;

    public ShipWithWaypoint(Waypoint waypoint) {
        super();
        this.waypoint = waypoint;
    }

    protected void turn(Action action) {
        this.waypoint.turn(action);
    }

    public void move(Action action) {
        if (action.isTurning()) {
            turn(action);
        } else if (action.isMoveForward()) {
            moveForward(action);
        } else if (action.isMoveNorth()) {
            waypoint.position.moveNorth(action.getSteps());
        } else if (action.isMoveSouth()) {
            waypoint.position.moveSouth(action.getSteps());
        } else if (action.isMoveEast()) {
            waypoint.position.moveEast(action.getSteps());
        } else if (action.isMoveWest()) {
            waypoint.position.moveWest(action.getSteps());
        }
    }

    protected void moveForward(Action action) {
        int updatedX = action.getSteps() * waypoint.position.x;
        int updatedY = action.getSteps() * waypoint.position.y;
        position = new Position(position.x + updatedX, position.y + updatedY);
    }
}
