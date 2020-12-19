import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShuttleSearchTest {

    @Test
    void shouldCalculateEarliestBusIdMultipliedByWaitingTimeInMin() {
        ShuttleSearch shuttleSearch = new ShuttleSearch();
        assertEquals(222, shuttleSearch.calculateEarliestBusIdMultipliedByWaitingTimeInMin());
    }

    @Test
    void shouldCalculateEarliestTimestampSuchThatAllIdsDepartAtOffsetsAsTheirPositionsInTheList() {
        ShuttleSearch shuttleSearch = new ShuttleSearch();
        assertEquals(new BigInteger("408270049879073"), shuttleSearch.calculateEarliestTimestampSuchThatAllIdsDepartAtOffsetsAsTheirPositionsInTheList());
    }
}