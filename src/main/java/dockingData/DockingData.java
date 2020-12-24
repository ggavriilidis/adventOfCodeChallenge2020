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

    public DockingData() {
        this.instructions = processFile();
    }

    public BigInteger memorySum() {
        instructions
            .forEach(i -> i.execute(mask, memory));
        BigInteger sum = BigInteger.ZERO;
        for (BigInteger n : memory.values()) {
            sum = sum.add(n);
        }
        return sum;
    }

    private List<Instruction> processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("docking-data-input.txt").toURI());
            return Files.lines(path)
                .map(this::toInstruction)
                .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Instruction toInstruction(String l) {
        String[] tokens = l.split("=");
        if ("mask".equals(tokens[0].trim())) {
            return new MaskInstruction(tokens[1].trim().toCharArray());
        } else {
            String mem = l.trim().substring(l.indexOf("[") + 1, l.indexOf("]"));
            return new MemoryInstruction(Integer.parseInt(mem), new BigInteger(tokens[1].trim()));
        }
    }
}
