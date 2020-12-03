import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TobogganTrajectoryTest {

    TobogganTrajectory tobogganTrajectory = new TobogganTrajectory();

    @Test
    void name() {
        assertEquals(282, tobogganTrajectory.getNumberOfTrees());
    }
}