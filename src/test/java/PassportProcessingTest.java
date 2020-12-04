import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassportProcessingTest {

    @Test
    void shouldCountTheNumberOfValidPassports() {
        PassportProcessing passportProcessing = new PassportProcessing();
        assertEquals(264L, passportProcessing.countValidPassports());
    }
}