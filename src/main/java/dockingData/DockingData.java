package dockingData;

import java.math.BigInteger;
import java.util.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DockingData {
    List<Instruction> instructions;
    Mask mask = new Mask();
    Map<Integer, BigInteger> memory = new HashMap<>();

    public BigInteger memorySum() {
        instructions = processFile()
                        .stream()
                        .map(this::toInstruction)
                        .collect(Collectors.toList());
        instructions
            .forEach(i -> i.execute(mask, memory));
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger n : memory.values()) {
            sum = sum.add(n);
        }
        return sum;
    }

    public int memorySumPartTwo() {
        instructions = processFile()
            .stream()
            .map(this::toInstructionPartTwo)
            .collect(Collectors.toList());
        return 0;
    }

    private List<String> processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("docking-data-input.txt").toURI());
            return Files.lines(path)
                .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Instruction toInstruction(String l) {
        String[] tokens = l.split("=");
        if (isFirstTokenMask(tokens[0])) {
            return new MaskInstruction(tokens[1].trim().toCharArray());
        } else {
            return new MemoryInstruction(Integer.parseInt(getMemoryAddress(l)), new BigInteger(tokens[1].trim()));
        }
    }

    private Instruction toInstructionPartTwo(String l) {
        String[] tokens = l.split("=");
        if (isFirstTokenMask(tokens[0])) {
            return new MaskInstruction(tokens[1].trim().toCharArray());
        } else {
            return new MemoryInstructionPartTwo(Integer.parseInt(getMemoryAddress(l)), new BigInteger(tokens[1].trim()));
        }
    }

    private boolean isFirstTokenMask(String token) {
        return "mask".equals(token.trim());
    }

    private String getMemoryAddress(String l) {
        return l.trim().substring(l.indexOf("[") + 1, l.indexOf("]"));
    }
}
