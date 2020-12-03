import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportRepair {

    private List<Integer> nums = processFile();

    public int getProductOfTwo() {
        Map<Integer, Integer> map = nums.stream().collect(Collectors.toMap(n -> n, n -> n));

        for (int i = 0; i < nums.size(); i++) {
            Integer currentNum = nums.get(i);
            Integer otherNum = map.get(2020 - currentNum);
            if (otherNum != null) {
                System.out.println("this num = " + currentNum);
                System.out.println("other num = " + otherNum);
                int result = otherNum * currentNum;
                System.out.println("result = " + result);
                return result;
            }
        }
        return -1;
    }

    public int getProductOfThree() {
        Map<Integer, Integer> map = nums.stream().collect(Collectors.toMap(n -> n, n -> n));
        List<Integer> sumOfTwo = nums.stream().map(n -> 2020 - n).collect(Collectors.toList());

        for (int i = 0; i < sumOfTwo.size(); i++) {
            Integer currentSumOfTwo = sumOfTwo.get(i);
            for (int j = 0; j < nums.size(); j++) {
                Integer first = map.get(currentSumOfTwo - nums.get(j));
                if (first != null) {
                    System.out.println("first num = " + first);
                    int second = sumOfTwo.get(i) - first;
                    System.out.println("second num = " + second);
                    int third = 2020 - sumOfTwo.get(i);
                    System.out.println("third num = " + third);
                    int result = first * second * third;
                    System.out.println("result = " + result);
                    return result;
                }
            }
        }
        return -1;
    }

    private List<Integer> processFile() {
        List<Integer> nums = new ArrayList<>();
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("report-input.txt").toURI());
            nums = Files.lines(path).map(Integer::parseInt).collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return nums;
    }
}
