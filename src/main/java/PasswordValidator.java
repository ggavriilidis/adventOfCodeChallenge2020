import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * --- Day 2: Password Philosophy ---
 * Your flight departs in a few days from the coastal airport; the easiest way down to the coast from here is via
 * toboggan.
 *
 * The shopkeeper at the North Pole Toboggan Rental Shop is having a bad day. "Something's wrong with our computers;
 * we can't log in!" You ask if you can take a look.
 *
 * Their password database seems to be a little corrupted: some of the passwords wouldn't have been allowed by the
 * Official Toboggan Corporate Policy that was in effect when they were chosen.
 *
 * To try to debug the problem, they have created a list (your puzzle input) of passwords (according to the corrupted
 * database) and the corporate policy when that password was set.
 *
 * For example, suppose you have the following list:
 *
 * 1-3 a: abcde
 * 1-3 b: cdefg
 * 2-9 c: ccccccccc
 * Each line gives the password policy and then the password. The password policy indicates the lowest and highest
 * number of times a given letter must appear for the password to be valid. For example, 1-3 a means that the
 * password must contain a at least 1 time and at most 3 times.
 *
 * In the above example, 2 passwords are valid. The middle password, cdefg, is not; it contains no instances of b,
 * but needs at least 1. The first and third passwords are valid: they contain one a or nine c, both within the
 * limits of their respective policies.
 *
 * How many passwords are valid according to their policies?
 */
public class PasswordValidator {

    public long getValidPasswords() {
        return getInputStreamFromFile("passwords-input.txt")
            .map(s -> s.split(" "))
            .map(this::toValidator)
            .filter(Validator::isValid)
            .count();
    }

    private Validator toValidator(String[] array) {
        String[] numOfOccurrencesTokens = array[0].split("-");
        return new Validator(new Boundary(
            Integer.parseInt(numOfOccurrencesTokens[0]),
            Integer.parseInt(numOfOccurrencesTokens[1])
        ), array[1].charAt(0), array[2]);
    }

    private Stream<String> getInputStreamFromFile(String fileName) {
        try {
            return Files.lines(Paths.get(getClass().getClassLoader()
                .getResource(fileName).toURI()));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Validator {
        Boundary boundary;
        Character letter;
        String password;

        private Validator(Boundary boundary, Character letter, String password) {
            this.boundary = boundary;
            this.letter = letter;
            this.password = password;
        }

        private boolean isValid() {
            List<Character> passwordChars = password.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
            Map<Character, Long> charToNumOfOccurrences = passwordChars.stream()
                .filter(c -> c.equals(letter))
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
            return charToNumOfOccurrences.entrySet()
                .stream()
                .filter(e -> isWithinBoundaries(e.getValue()))
                .count() == charToNumOfOccurrences.size() && charToNumOfOccurrences.size() != 0;
        }

        private boolean isWithinBoundaries(long value) {
            return boundary.lower <= value && value <= boundary.upper;
        }
    }

    private static class Boundary {
        int lower;
        int upper;

        public Boundary(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }
}
