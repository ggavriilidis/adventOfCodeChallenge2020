import rainrisk.Action;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        BigInteger firstId = new BigInteger(busIdsIncludingXs.get(0));
        BigInteger multiplier = new BigInteger("100000000000000").divide(firstId);
        BigInteger timestamp = firstId.multiply(multiplier);
        while (!isGivenTimestamp(timestamp)) {
            multiplier = multiplier.add(BigInteger.ONE);
            timestamp = firstId.multiply(multiplier);;
        }
        return timestamp;
    }

    private boolean isGivenTimestamp(BigInteger timestamp) {
        for (int i = 1; i < busIdsIncludingXs.size(); i++) {
            String currentId = busIdsIncludingXs.get(i);
            if ("x".equals(currentId)) {
                timestamp = timestamp.add(BigInteger.ONE);
                continue;
            } else {
                if (!((timestamp.add(BigInteger.ONE)).mod(new BigInteger(currentId)).equals(BigInteger.ZERO))) {
                    return false;
                }
                timestamp = timestamp.add(BigInteger.ONE);
            }
        }
        return true;
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
