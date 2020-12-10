import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandyHaversacksTest {

    @Test
    void shouldCountBagColoursThatContainShinyGold() {
        HandyHaversacks handyHaversacks = new HandyHaversacks();

        assertEquals(208, handyHaversacks.countBagColoursThatContainColour("shiny gold"));
    }
}