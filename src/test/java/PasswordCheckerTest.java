import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordCheckerTest {

    PasswordValidator passwordValidator = new PasswordValidator();
    @Test
    void getValidPassWordsShouldReturnTheNumberOfValidPasswordsBasedOnTheRules() {
        assertEquals(2, passwordValidator.getValidPasswords());
    }
}