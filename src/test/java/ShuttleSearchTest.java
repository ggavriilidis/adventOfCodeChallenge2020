import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleSearchTest {

    @Test
    void shouldCalculateEarliestBusIdMultipliedByWaitingTimeInMin() {
        ShuttleSearch shuttleSearch = new ShuttleSearch();
        assertEquals(222, shuttleSearch.calculateEarliestBusIdMultipliedByWaitingTimeInMin());
    }

    @Test
    void shouldCalculateEarliestTimestampSuchThatAllIdsDepartAtOffsetsAsTheirPositionsInTheList() {
        ShuttleSearch shuttleSearch = new ShuttleSearch();
        assertEquals(779210, shuttleSearch.calculateEarliestTimestampSuchThatAllIdsDepartAtOffsetsAsTheirPositionsInTheList());
    }
}