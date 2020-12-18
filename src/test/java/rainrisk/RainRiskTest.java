package rainrisk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RainRiskTest {

    @Test
    void shouldcalculateManhattanDistanceNoWaypoint() {
        RainRisk rainRisk = new RainRisk();
        assertEquals(2879, rainRisk.calculateManhattanDistanceNoWaypoint());
    }

    @Test
    void shouldcalculateManhattanDistanceWithWaypoint() {
        RainRisk rainRisk = new RainRisk();
        assertEquals(178986, rainRisk.calculateManhattanDistanceWithWaypoint());
    }
}