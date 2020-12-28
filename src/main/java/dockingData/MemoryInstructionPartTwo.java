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
        List<int[]> floatingMemoryAddresses = getFloatingValues(maskedMemoryAddress)
            .stream()
            .map(s -> s.chars().mapToObj(Character::getNumericValue).mapToInt(i -> i).toArray())
            .collect(Collectors.toList());
        floatingMemoryAddresses.stream().map(this::convertFromBinary).forEach(fMA -> memory.put(fMA, memoryValue));
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

    static List<String> getFloatingValues(String mask) {
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
