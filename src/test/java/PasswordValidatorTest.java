import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    PasswordValidator passwordValidator = new PasswordValidator();
    @Test
    void getValidPasswordsWithinBoundariesShouldReturnTheNumberOfValidPasswordsWhoseGivenLetterIsWithinTheUpperAndLowerBoundaries() {
        assertEquals(636, passwordValidator.getValidPasswordsWithinBoundaries());
    }

    @Test
    void getValidPasswordsWhichAppearOnceInGivenPositionsShouldReturnTheNumeberOfValidPasswordsWhoseGivenLetterAppearsInOneOfTheGivenPositions() {
        assertEquals(588, passwordValidator.getValidPasswordsWhichAppearOnceInGivenPositions());
    }
}