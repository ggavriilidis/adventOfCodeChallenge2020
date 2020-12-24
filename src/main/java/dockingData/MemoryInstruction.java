package dockingData;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryInstruction implements Instruction {

    private int memoryPosition;
    private BigInteger memoryValue;

    public MemoryInstruction(int memoryPosition, BigInteger memoryValue) {
        this.memoryPosition = memoryPosition;
        this.memoryValue = memoryValue;
    }

    @Override
    public void execute(Mask mask, Map<Integer, BigInteger> memory) {
        int[] binary = convertToBinary();
        int[] masked = applyMask(mask.getMask(), reverse(binary));
        memory.put(memoryPosition, convertFromBinary(reverse(masked)));
    }

    private BigInteger convertFromBinary(int[] ar) {
        BigInteger sum = BigInteger.ZERO;
        for (int i = 0; i < ar.length; i ++) {
            if (ar[i] == 1) {
//                sum += Math.pow(2, i);
                sum = sum.add(power(2, i));
            }
        }
        return sum;
    }

    private int[] reverse(int[] ar) {
        List<Integer> list = Arrays.stream(ar).boxed().collect(Collectors.toList());
        Collections.reverse(list);
        for (int i = 0; i < list.size(); i++) {
            ar[i] = list.get(i);
        }
        return ar;
    }

    private int[] applyMask(char[] mask, int[] original) {
        for (int i = 0; i < mask.length; i ++) {
            if(mask[i] == '0' || mask[i] == '1') {
                original[i] = Character.getNumericValue(mask[i]);
            }
        }
        return original;
    }

    private int[] convertToBinary() {
        int[] binaryValue = new int[36];
        while (memoryValue.compareTo(BigInteger.ZERO) > 0) {
            int intLog2 = log2(memoryValue);
            binaryValue[intLog2] = 1;
//            double pow2 = Math.pow(2, intLog2);
            memoryValue = memoryValue.subtract(power(2, intLog2));
        }
        return binaryValue;
    }

    private BigInteger power(int base, int exponent) {
        if (exponent == 0) {
            return BigInteger.ONE;
        }
        if (exponent == 1) {
            return BigInteger.valueOf(base);
        }
        return BigInteger.valueOf(base).multiply(power(base, exponent - 1));
    }

    private static int log2(BigInteger x)
    {
        int log2 = 0;
        while (!x.divide(BigInteger.TWO).equals(BigInteger.ZERO)) {
            x = x.divide(BigInteger.TWO);
            log2++;
        }
        return log2;
//        return Math.log(x) / Math.log(2);
     }
}
