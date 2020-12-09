import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncodingErrorTest {

    @Test
    void shouldCalculateNumberWhichIsNotTheSumOfPreviousNumbers() {
        EncodingError encodingError = new EncodingError();

        assertEquals(104054607, encodingError.calculateNumberWhichIsNotTheSumOfPreviousNumbers(25));
    }

    @Test
    void shouldCalculateTheContiguousSetOfAtLeastTwoNumbersWhichSumToTheInvalidNumber() {
        EncodingError encodingError = new EncodingError();

        assertEquals(13935797, encodingError.calculateContiguousSetOfNumbersWhichSumToTheInvalidNumber(25));
    }
}