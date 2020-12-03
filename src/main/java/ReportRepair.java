import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * --- Day 1: Report Repair ---
 * After saving Christmas five years in a row, you've decided to take a vacation at a nice resort on a tropical
 * island. Surely, Christmas will go on without you.
 *
 * The tropical island has its own currency and is entirely cash-only. The gold coins used there have a little
 * picture of a starfish; the locals just call them stars. None of the currency exchanges seem to have heard of them,
 * but somehow, you'll need to find fifty of these coins by the time you arrive so you can pay the deposit on your room.
 *
 * To save your vacation, you need to get all fifty stars by December 25th.
 *
 * Collect stars by solving puzzles. Two puzzles will be made available on each day in the Advent calendar; the
 * second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!
 *
 * Before you leave, the Elves in accounting just need you to fix your expense report (your puzzle input);
 * apparently, something isn't quite adding up.
 *
 * Specifically, they need you to find the two entries that sum to 2020 and then multiply those two numbers together.
 *
 * For example, suppose your expense report contained the following:
 *
 * 1721
 * 979
 * 366
 * 299
 * 675
 * 1456
 * In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying them together produces 1721 * 299 =
 * 514579, so the correct answer is 514579.
 *
 * Of course, your expense report is much larger. Find the two entries that sum to 2020; what do you get if you
 * multiply them together?
 *
 * --- Part Two ---
 * The Elves in accounting are thankful for your help; one of them even offers you a starfish coin they had left over
 * from a past vacation. They offer you a second one if you can find three numbers in your expense report that meet
 * the same criteria.
 *
 * Using the above example again, the three entries that sum to 2020 are 979, 366, and 675. Multiplying them together
 * produces the answer, 241861950.
 *
 * In your expense report, what is the product of the three entries that sum to 2020?
 */

public class ReportRepair {

    private final List<Integer> numbers = processFile();

    public int getProductOfTwo() {
        Map<Integer, Integer> map = numbers.stream().collect(Collectors.toMap(n -> n, n -> n));

        for (int i = 0; i < numbers.size(); i++) {
            Integer currentNum = numbers.get(i);
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
        Map<Integer, Integer> map = numbers.stream().collect(Collectors.toMap(n -> n, n -> n));
        List<Integer> sumOfTwo = numbers.stream().map(n -> 2020 - n).collect(Collectors.toList());

        for (int i = 0; i < sumOfTwo.size(); i++) {
            Integer currentSumOfTwo = sumOfTwo.get(i);
            for (int j = 0; j < numbers.size(); j++) {
                Integer first = map.get(currentSumOfTwo - numbers.get(j));
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
