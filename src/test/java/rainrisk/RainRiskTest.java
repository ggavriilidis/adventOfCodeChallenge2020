package rainrisk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RainRiskTest {

    @Test
    void shouldcalculateManhattanDistance() {
        RainRisk rainRisk = new RainRisk();
        assertEquals(2879, rainRisk.calculateManhattanDistance());
    }
}