import java.io.IOException;
import java.math.BigInteger;
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
    List<String> busIdsIncludingXs;

    public ShuttleSearch() {
        processFile();
    }

    public int calculateEarliestBusIdMultipliedByWaitingTimeInMin() {
        int earliestBusTimeStamp =
            busIds.stream().mapToInt(i -> i).map(id -> earliestEstimate / id * id + id).min().getAsInt();
        Integer busId = busIds.stream().filter(id -> earliestBusTimeStamp % id == 0).findFirst().get();
        return (earliestBusTimeStamp - earliestEstimate) * busId;
    }

    public BigInteger calculateEarliestTimestampSuchThatAllIdsDepartAtOffsetsAsTheirPositionsInTheList() {
        BigInteger increment = BigInteger.ONE;
        BigInteger timestamp = BigInteger.ZERO;

        for (int i = 0; i < busIdsIncludingXs.size(); i++) {
            String currentBusId = busIdsIncludingXs.get(i);
            if (!"x".equals(currentBusId)) {
                while (!timestamp.add(BigInteger.valueOf(i)).mod(new BigInteger(currentBusId)).equals(BigInteger.ZERO)) {
                    timestamp = timestamp.add(increment);
                }
                increment = increment.multiply(new BigInteger(currentBusId));
            }
        }
        return timestamp;
    }

    private void processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("shuttle-search-input.txt").toURI());
            List<String> lines = Files.lines(path)
                .collect(Collectors.toList());
            earliestEstimate = Integer.parseInt(lines.get(0));
            busIdsIncludingXs = Arrays.stream(lines.get(1).split(",")).collect(Collectors.toList());
            busIds = busIdsIncludingXs.stream().filter(l -> !"x".equals(l)).map(Integer::parseInt).collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
