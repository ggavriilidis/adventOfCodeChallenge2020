import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdapterArrayTest {
    @Test
    void calculateNumOfOneJoltDiffMultipliedByThreeJoltDiff() {
        AdapterArray adapterArray = new AdapterArray();
        assertEquals(3000, adapterArray.calculateNumOfOneJoltDiffMultipliedByThreeJoltDiff());
    }
}