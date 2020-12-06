import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomCustomsTest {

    @Test
    void shouldCountTheSumOfAllYesAnswersAnyoneAnswered() {
        CustomCustoms customCustoms = new CustomCustoms();

        assertEquals(6457, customCustoms.countTheSumOfAllYesAnswersAnyone());
    }

    @Test
    void shouldCountTheSumOfAllYesAnswersEveryoneAnswered() {
        CustomCustoms customCustoms = new CustomCustoms();

        assertEquals(3260, customCustoms.countTheSumOfAllYesAnswersEveryone());
    }
}