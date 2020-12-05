import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryBoarding {

    private List<String> boardingPasses;
    private static final int ROWS[] = initArray(128);
    private static final int COLUMNS[] = initArray(8);

    private static int[] initArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i ++) {
            arr[i] = i;
        }
        return arr;
    }

    public BinaryBoarding() {
        this.boardingPasses = processFile();;
    }

    public int calculateHighestSeatId() {
        return calculateSeatIds().stream().max(Integer::compareTo).orElse(-1);
    }

    private List<Integer> calculateSeatIds() {
        List<Integer> seatIds = boardingPasses.stream().map(bp -> calculateSeatId(
            binarySearch(ROWS, bp.substring(0, 7)),
            binarySearch(COLUMNS, bp.substring(7, 10))
        ))
            .sorted()
            .collect(Collectors.toList());
        return seatIds;
    }

    public int findMySeat() {
        List<Integer> allSeats = calculateSeatIds();
        int a = allSeats.get(1);
        for (int i =0; i < allSeats.size(); i++) {
            if (i > 0 && i < allSeats.size() - 1) {
                if (allSeats.get(i) != a) {
                    return a;
                }
                a++;
            }
        }
        return -1;
    }

    private int calculateSeatId(int row, int column) {
        return row * 8 + column;
    }

    private int binarySearch(int[] arr, String rowCharacters) {
        if (arr.length == 1) {
            return arr[0];
        }
        else if (rowCharacters.charAt(0) == 'B' || rowCharacters.charAt(0) == 'R') {
            return binarySearch(Arrays.copyOfRange(arr, arr.length / 2, arr.length), rowCharacters.substring(1));
        } else {
            return binarySearch(Arrays.copyOfRange(arr, 0, arr.length / 2), rowCharacters.substring(1));
        }
    }

    private List<String> processFile() {
        try {
            return Files.lines(Paths.get(getClass().getClassLoader()
                .getResource("binary-boarding-input.txt").toURI()))
                .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
