package dockingData;

import java.math.BigInteger;
import java.util.Map;

public class MaskInstruction implements Instruction {

    private char[] mask;

    public MaskInstruction(char[] mask) {
        this.mask = mask;
    }

    public void execute(Mask mask, Map<BigInteger, BigInteger> memory) {
        mask.setMask(this.mask);
    }

}
