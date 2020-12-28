package dockingData;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static dockingData.MemoryInstructionPartTwo.getFloatingValues;
import static org.junit.jupiter.api.Assertions.*;

class MemoryInstructionPartTwoTest {

    @Test
    void shouldGetFloatingValues() {
        List<String> floatingBinaryArrays = getFloatingValues("X1001X");

        assertEquals(Arrays.asList("110011",
            "110010",
            "010011",
            "010010"
        ), floatingBinaryArrays);
    }
}