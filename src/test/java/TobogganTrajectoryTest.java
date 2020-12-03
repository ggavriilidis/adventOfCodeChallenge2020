import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TobogganTrajectoryTest {

    TobogganTrajectory tobogganTrajectory = new TobogganTrajectory();

    @Test
    void shouldGetTheNumberOfTreesForASlope() {
        assertEquals(282, tobogganTrajectory.getNumberOfTrees(3, 1));
    }

    @Test
    void shouldGetTheProductOfNumberOfTreesForAListOfSlopes() {
        assertEquals(958815792, tobogganTrajectory.getProductOfNumberOfTrees(Arrays.asList(
            new TobogganTrajectory.Slope(1, 1),
            new TobogganTrajectory.Slope(3, 1),
            new TobogganTrajectory.Slope(5, 1),
            new TobogganTrajectory.Slope(7, 1),
            new TobogganTrajectory.Slope(1, 2)
        )));
    }
}