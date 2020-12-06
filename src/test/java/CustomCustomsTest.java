import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomCustomsTest {

    @Test
    void shouldCountTheSumOfAllYesAnswers() {
        CustomCustoms customCustoms = new CustomCustoms();

        assertEquals(6457, customCustoms.countTheSumOfAllYesAnswers());
    }
}