import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassportProcessingTest {

    @Test
    void shouldCountTheNumberOfValidPassportsWithMandatoryFieldsPresent() {
        PassportProcessing passportProcessing = new PassportProcessing();
        assertEquals(264L, passportProcessing.countPassportsWithMandatoryFieldsPresent());
    }

    @Test
    void shouldCountTheNumberOfValidPassportsWithStrictFieldValidation() {
        PassportProcessing passportProcessing = new PassportProcessing();
        assertEquals(224L, passportProcessing.countValidPassports());
    }
}