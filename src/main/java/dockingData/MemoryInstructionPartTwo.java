package dockingData;

import java.math.BigInteger;
import java.util.Map;

public class MemoryInstructionPartTwo extends MemoryInstruction {

    public MemoryInstructionPartTwo(int memoryPosition, BigInteger memoryValue) {
        super(memoryPosition, memoryValue);
    }

    @Override
    public void execute(Mask mask, Map<Integer, BigInteger> memory) {

        super.execute(mask, memory);
    }
}
