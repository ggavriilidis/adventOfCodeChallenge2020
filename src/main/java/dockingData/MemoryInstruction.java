package dockingData;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryInstruction implements Instruction {

    protected int memoryAddress;
    protected BigInteger memoryValue;

    public MemoryInstruction(int memoryAddress, BigInteger memoryValue) {
        this.memoryAddress = memoryAddress;
        this.memoryValue = memoryValue;
    }

    @Override
    public void execute(Mask mask, Map<BigInteger, BigInteger> memory) {
        int[] binary = convertToBinary(memoryValue);
        int[] masked = applyMask(mask.getMask(), binary);
        memory.put(BigInteger.valueOf(memoryAddress), convertFromBinary(masked));
    }

    protected BigInteger convertFromBinary(int[] ar) {
        BigInteger sum = BigInteger.ZERO;
        int[] reverse = reverse(ar);
        for (int i = 0; i < ar.length; i ++) {
            if (reverse[i] == 1) {
//                sum += Math.pow(2, i);
                sum = sum.add(power(2, i));
            }
        }
        return sum;
    }

    protected int[] reverse(int[] ar) {
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

    protected int[] convertToBinary(BigInteger memoryValue) {
        int[] binaryValue = new int[36];
        while (memoryValue.compareTo(BigInteger.ZERO) > 0) {
            int intLog2 = log2(memoryValue);
            binaryValue[intLog2] = 1;
//            double pow2 = Math.pow(2, intLog2);
            memoryValue = memoryValue.subtract(power(2, intLog2));
        }
        return reverse(binaryValue);
    }

    protected BigInteger power(int base, int exponent) {
        if (exponent == 0) {
            return BigInteger.ONE;
        }
        if (exponent == 1) {
            return BigInteger.valueOf(base);
        }
        return BigInteger.valueOf(base).multiply(power(base, exponent - 1));
    }

    protected static int log2(BigInteger x)
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
