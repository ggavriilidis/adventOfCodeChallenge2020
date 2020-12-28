package dockingData;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class DockingDataTest {

    @Test
    void shouldCalculateMemorySum() {
        DockingData dockingData = new DockingData();

        assertEquals(new BigInteger("11926135976176"), dockingData.memorySum());
    }

    @Test
    void shouldCalculateMemorySumPartTwo() {
        DockingData dockingData = new DockingData();

        assertEquals(new BigInteger("4330547254348"), dockingData.memorySumPartTwo());
    }
}