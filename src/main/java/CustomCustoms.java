import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCustoms {

    public int countTheSumOfAllYesAnswers() {
        List<String> groupAnswers = processFile();
        Stream<Stream<Character>> streamStream = groupAnswers.stream()
            .map(gA -> gA.chars().mapToObj(c -> (char) c));
        return streamStream
            .map(chars ->
                chars.collect(Collectors.groupingBy(c -> c)))
            .map(Map::size).mapToInt(i -> i).sum();
    }

    private List<String> processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("custom-customs-input.txt").toURI());
            return Arrays.stream(Files.lines(path)
                .collect(Collectors.joining("\n")).split("(?m)^\\n"))
                .map(s -> s.replaceAll("\\n", ""))
                .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
