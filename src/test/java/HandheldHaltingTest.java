import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandheldHaltingTest {

    @Test
    void shouldCalculateAccumulatorValueBeforeInstructionExecutedSecondTime() {
        HandheldHalting handheldHalting = new HandheldHalting();

        assertEquals(1859, handheldHalting.calculateAccumulatorValueBeforeInstructionExecutedTwice());
    }

    @Test
    void shouldCalculateAccumulatorValueAfterTheProgramTerminates() {
        HandheldHalting handheldHalting = new HandheldHalting();

        assertEquals(1235, handheldHalting.calculateAccumulatorValueAfterProgramTerminates());
    }
}