import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class SeatingSystem {

    private Seat[][] layout;

    public SeatingSystem() {
        this.layout = processFile();
    }

    public int calculateOccupiedSeatNumWhenLayoutStopsChanging() {
        Seat[][] nextRoundLayout = new Seat[layout.length][layout[0].length];
        while (!Arrays.deepEquals(nextRoundLayout, layout)) {
            Seat[][] tempLayout = layout;
            for (int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[i].length; j++) {
                    nextRoundLayout[i][j] = layout[i][j].nextRound(calculateOccupiedAdjacentSeats(i, j));
                }
            }
            layout = nextRoundLayout;
            nextRoundLayout = tempLayout;
        }
        return (int) Arrays.stream(layout).flatMap(Arrays::stream).filter(Seat::isOccupied).count();
    }

    private int calculateOccupiedAdjacentSeats(int i, int j) {
        Optional<Integer> top = i > 0 ? Optional.of(layout[i-1][j]).map(this::getOccupiedNum) : Optional.empty();
        Optional<Integer> topLeft = i > 0 && j > 0 ? Optional.of(layout[i-1][j-1]).map(this::getOccupiedNum) : Optional.empty();
        Optional<Integer> topRight = i > 0 && j < layout[i].length - 1 ? Optional.of(layout[i-1][j+1]).map(this::getOccupiedNum) : Optional.empty();
        Optional<Integer> left = j > 0 ? Optional.of(layout[i][j-1]).map(this::getOccupiedNum) : Optional.empty();
        Optional<Integer> right = j < layout[i].length - 1 ? Optional.of(layout[i][j+1]).map(this::getOccupiedNum) : Optional.empty();
        Optional<Integer> bottom = i < layout.length - 1 ? Optional.of(layout[i+1][j]).map(this::getOccupiedNum) : Optional.empty();
        Optional<Integer> bottomLeft = i < layout.length - 1 && j > 0 ? Optional.of(layout[i+1][j-1]).map(this::getOccupiedNum) : Optional.empty();
        Optional<Integer> bottomRight = i < layout.length - 1 && j < layout[i].length - 1 ? Optional.of(layout[i+1][j+1]).map(this::getOccupiedNum) : Optional.empty();

        return Stream.of(top,topLeft,topRight, left, right, bottom, bottomLeft, bottomRight).filter(Optional::isPresent).mapToInt(Optional::get).sum();
    }

    private int getOccupiedNum(Seat s) {
        return s.isOccupied() ? 1 : 0;
    }

    public static class Seat {
        private boolean isOccupied;

        public Seat(boolean isOccupied) {
            this.isOccupied = isOccupied;
        }

        public Seat() {
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public Seat nextRound(int occupiedAdjacentSeats) {
            if (occupiedAdjacentSeats == 0) {
                return new Seat(true);
            } else if (occupiedAdjacentSeats >= 4) {
                return new Seat(false);
            }
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Seat seat = (Seat) o;
            return isOccupied() == seat.isOccupied();
        }

        @Override
        public int hashCode() {
            return Objects.hash(isOccupied());
        }
    }

    public static class Floor extends Seat {

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Seat nextRound(int occupiedAdjacentSeats) {
            return this;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Floor seat = (Floor) o;
            return isOccupied() == seat.isOccupied();
        }
    }

    private Seat[][] processFile() {
        try {
            Path path = Paths.get(getClass().getClassLoader()
                .getResource("seating-system-input.txt").toURI());
            return Files.lines(path)
                .map(l -> l.chars().mapToObj(c -> (char) c))
                .map(chars -> chars.map(this::convertCharToSeat).toArray(Seat[]::new))
                .toArray(Seat[][]::new);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Seat convertCharToSeat(Character c) {
        if (c.equals('L')) {
            return new Seat(false);
        } else if (c.equals('#')) {
            return new Seat(false);
        } else if (c.equals('.')) {
            return new Floor();
        }
        throw new RuntimeException("Not able to parse unknown character: " + c);
    }
}
