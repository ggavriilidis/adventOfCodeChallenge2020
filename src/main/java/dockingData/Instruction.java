package dockingData;

import java.math.BigInteger;
import java.util.Map;

public interface Instruction {

    void execute(Mask mask, Map<BigInteger, BigInteger> memory);
}
