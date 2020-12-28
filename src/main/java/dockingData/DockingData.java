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
    Mask mask;
    Map<BigInteger, BigInteger> memory;

    public BigInteger memorySum() {
        mask = new Mask();
        memory = new HashMap<>();
        processFile()
            .stream()
            .map(this::toInstruction)
            .forEach(i -> i.execute(mask, memory));
        return memory.values().stream().reduce(BigInteger::add).orElse(BigInteger.ZERO);
    }

    public BigInteger memorySumPartTwo() {
        mask = new Mask();
        memory = new HashMap<>();
        processFile()
            .stream()
            .map(this::toInstructionPartTwo)
            .forEach(i -> i.execute(mask, memory));
        return memory.values().stream().reduce(BigInteger::add).orElse(BigInteger.ZERO);
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
