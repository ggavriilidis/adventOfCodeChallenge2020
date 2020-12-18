import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SeatingSystemTest {

    SeatingSystem seatingSystem = new SeatingSystem();

    @Test
    void shouldCalculateOccupiedSeatNumWhenLayoutStopsChanging() {

        assertEquals(2344, seatingSystem.calculateOccupiedSeatNumWhenLayoutStopsChanging());
    }

    @Test
    void shouldCalculateOccupiedSeatsWithNewVisibilityRules() {
        assertEquals(2076, seatingSystem.calculateOccupiedSeatsWithNewVisibilityRules());
    }

    @Test
    void shouldBeAbleToCreateAnOccupiedSeat() {
        SeatingSystem.Seat seat = new SeatingSystem.Seat(true);
        assertTrue(seat.isOccupied());
    }

    @Test
    void shouldBeAbleToCreateAnUnoccupiedSeat() {
        SeatingSystem.Seat seat = new SeatingSystem.Seat(false);
        assertFalse(seat.isOccupied());
    }

    @Test
    void anEmptySeatBecomesOccupiedWhenNoOtherSeatsAdjacentToIt() {
        SeatingSystem.Seat seat = new SeatingSystem.Seat(false);
        SeatingSystem.Seat nextRoundSeat = seat.nextRound(0);
        assertTrue(nextRoundSeat.isOccupied());
    }

    @ParameterizedTest(name = "#{index} - A seat with an occupied state {0} should become {2} when there are {1} seats adjacent to it")
    @CsvSource({
        "true, 4, false",
        "true, 5, false",
        "true, 3, true",
        "false, 3, false",
        "false, 4, false",
        "false, 5, false",
    })
    void anOccupiedSeatBecomesEmptyWhenFourOrMoreSeatsAdjacentToIt(boolean initialState, int numberOfAdjacentSeats, boolean finalState) {
        SeatingSystem.Seat seat = new SeatingSystem.Seat(initialState);
        SeatingSystem.Seat nextRoundSeat = seat.nextRound(numberOfAdjacentSeats);
        assertEquals(finalState, nextRoundSeat.isOccupied());
    }

    @ParameterizedTest(name = "#{index} - A seat with an occupied state {0} should remain {0} when there are {1} seats adjacent to it")
    @CsvSource({
        "true,      1",
        "true,      2",
        "true,      3",
        "false,      1",
        "false,      2",
        "false,      3",
    })
    void aSeatStateShouldNotChangeWhenOneTwoOrThreeSeatsAdjacentToIt(boolean state, int numberOfAdjacentSeats) {
        SeatingSystem.Seat seat = new SeatingSystem.Seat(state);

        SeatingSystem.Seat nextRoundSeat = seat.nextRound(numberOfAdjacentSeats);
        assertEquals(state, nextRoundSeat.isOccupied());
    }
}