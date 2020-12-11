import java.util.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class AdapterArray {

    public int calculateNumOfOneJoltDiffMultipliedByThreeJoltDiff() {
        List<Integer> adapters = processFile();
        Map<Integer,Integer> diffToCount = new HashMap<>();
        for (int i = 1; i < adapters.size(); i++) {
            Integer diff = adapters.get(i) - adapters.get(i - 1);
            diffToCount.computeIfPresent(diff, (k, v) -> v + 1);
            diffToCount.putIfAbsent(diff, 1);
        }
        return (diffToCount.get(3) + 1) * (diffToCount.get(1) + 1);
    }

    private List<Integer> processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("adapter-array-input.txt").toURI());
            return Files.lines(path)
                .map(Integer::valueOf)
                .sorted()
                .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
