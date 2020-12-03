import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    PasswordValidator passwordValidator = new PasswordValidator();
    @Test
    void getValidPassWordsShouldReturnTheNumberOfValidPasswordsBasedOnTheRules() {
        assertEquals(636, passwordValidator.getValidPasswords());
    }
}