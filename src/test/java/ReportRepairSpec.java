import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportRepairTest {

    ReportRepair reportRepair = new ReportRepair();

    @Test
    void getProductOfTwoShouldReturnTheProductOfTheTwoNumbersThatSumTo2020() {
        assertEquals(471019,  reportRepair.getProductOfTwo());
    }

    @Test
    void getProductOfThreeShouldReturnTheProductOfTheThreeNumbersThatSumTo2020() {
        assertEquals(103927824,  reportRepair.getProductOfThree());
    }
}