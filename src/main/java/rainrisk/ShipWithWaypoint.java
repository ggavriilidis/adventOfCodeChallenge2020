package rainrisk;

public class ShipWithWaypoint extends Ship {

    private Waypoint waypoint;

    public ShipWithWaypoint(Waypoint waypoint) {
        super();
        this.waypoint = waypoint;
    }

    public void move(Action action) {
        if (action.isMoveForward()) {
            moveForward(action);
        } else {
            waypoint.move(action);
        }
    }

    protected void moveForward(Action action) {
        int updatedX = action.getSteps() * waypoint.position.x;
        int updatedY = action.getSteps() * waypoint.position.y;
        position = new Position(position.x + updatedX, position.y + updatedY);
    }
}
