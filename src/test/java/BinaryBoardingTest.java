import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryBoardingTest {

    @Test
    void shouldCalculateHighestSeatId() {
        BinaryBoarding binaryBoarding = new BinaryBoarding();
        assertEquals(955, binaryBoarding.calculateHighestSeatId());
    }

    @Test
    void shouldFindMySeat() {
        BinaryBoarding binaryBoarding = new BinaryBoarding();
        assertEquals(569, binaryBoarding.findMySeat());
    }
}