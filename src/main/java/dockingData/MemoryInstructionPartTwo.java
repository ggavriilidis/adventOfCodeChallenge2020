package dockingData;

import java.util.*;
import java.math.BigInteger;
import java.util.Map;
import java.util.stream.Collectors;

public class MemoryInstructionPartTwo extends MemoryInstruction {

    public MemoryInstructionPartTwo(int memoryAddress, BigInteger memoryValue) {
        super(memoryAddress, memoryValue);
    }

    @Override
    public void execute(Mask mask, Map<BigInteger, BigInteger> memory) {
        int[] binaryMemAddress = convertToBinary(BigInteger.valueOf(memoryAddress));
        String maskedMemoryAddress = applyMask(mask.getMask(), binaryMemAddress);
        List<String> floatingMemoryAddresses = getFloatingValues(maskedMemoryAddress);
        for (String floatingMemoryAddress : floatingMemoryAddresses) {
            List<Integer> ints = floatingMemoryAddress.chars().mapToObj(Character::getNumericValue).collect(Collectors.toList());
            memory.put(convertFromBinary(reverse(convertIntegers(ints))), memoryValue);
        }
    }

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i);
        }
        return ret;
    }

    private String applyMask(char[] mask, int[] original) {
        char[] masked = new char[mask.length];
        for (int i = 0; i < mask.length; i ++) {
                if ('1' == mask[i]) {
                    masked[i] = '1';
                } else if ('X' == mask[i]) {
                    masked[i] = 'X';
                }
                else {
                    masked[i] = (char) ('0' + original[i]);
                }
            }
        return new String(masked);
    }

    public List<String> getFloatingValues(String mask) {
        if (mask.indexOf('X') == -1) {
            return Arrays.asList(mask);
        }
        List<String> result = new ArrayList<>();
        String m1 = mask.replaceFirst("X", "1");
        String m2 = mask.replaceFirst("X", "0");
        result.addAll(getFloatingValues(m1));
        result.addAll(getFloatingValues(m2));
        return result;
    }
}
