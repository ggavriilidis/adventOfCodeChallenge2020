import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PasswordValidator {

    public long getValidPasswords() {
        return getInputStreamFromFile("passwords-input.txt")
            .map(s -> s.split(" "))
            .map(ar -> new Validator(new Boundary(
                Integer.parseInt(ar[0].substring(0, 1)),
                Integer.parseInt(ar[0].substring(2, 3))
            ), ar[1].charAt(0), ar[2]))
            .filter(Validator::isValid)
            .count();
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
