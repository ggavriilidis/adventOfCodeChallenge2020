import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportRepairTest {

    ReportRepair reportRepair = new ReportRepair();

    @Test
    void getProductOfTwoShouldReturnTheProductOfTheTwoNumbersThatSumTo2020() {
        assertEquals(259716,  reportRepair.getProductOfTwo());
    }

    @Test
    void getProductOfThreeShouldReturnTheProductOfTheThreeNumbersThatSumTo2020() {
        assertEquals(120637440,  reportRepair.getProductOfThree());
    }
}