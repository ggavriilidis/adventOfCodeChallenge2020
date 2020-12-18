import rainrisk.Action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShuttleSearch {

    private int earliestEstimate;
    private List<Integer> busIds;

    public ShuttleSearch() {
        processFile();
    }

    public int calculateEarliestBusIdMultipliedByWaitingTimeInMin() {
        int earliestBusTimeStamp =
            busIds.stream().mapToInt(i -> i).map(id -> earliestEstimate / id * id + id).min().getAsInt();
        Integer busId = busIds.stream().filter(id -> earliestBusTimeStamp % id == 0).findFirst().get();
        return (earliestBusTimeStamp - earliestEstimate) * busId;
    }

    private void processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("shuttle-search-input.txt").toURI());
            List<String> lines = Files.lines(path)
                .collect(Collectors.toList());
            earliestEstimate = Integer.parseInt(lines.get(0));
            busIds = Arrays.stream(lines.get(1).split(",")).filter(l -> !"x".equals(l)).map(Integer::parseInt).collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
