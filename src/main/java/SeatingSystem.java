import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Your plane lands with plenty of time to spare. The final leg of your journey is a ferry that goes directly to the
 * tropical island where you can finally start your vacation. As you reach the waiting area to board the ferry, you
 * realize you're so early, nobody else has even arrived yet!
 *
 * By modeling the process people use to choose (or abandon) their seat in the waiting area, you're pretty sure you
 * can predict the best place to sit. You make a quick map of the seat layout (your puzzle input).
 *
 * The seat layout fits neatly on a grid. Each position is either floor (.), an empty seat (L), or an occupied seat
 * (#). For example, the initial seat layout might look like this:
 *
 * L.LL.LL.LL
 * LLLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLLL
 * L.LLLLLL.L
 * L.LLLLL.LL
 * Now, you just need to model the people who will be arriving shortly. Fortunately, people are entirely predictable
 * and always follow a simple set of rules. All decisions are based on the number of occupied seats adjacent to a
 * given seat (one of the eight positions immediately up, down, left, right, or diagonal from the seat). The
 * following rules are applied to every seat simultaneously:
 *
 * If a seat is empty (L) and there are no occupied seats adjacent to it, the seat becomes occupied.
 * If a seat is occupied (#) and four or more seats adjacent to it are also occupied, the seat becomes empty.
 * Otherwise, the seat's state does not change.
 * Floor (.) never changes; seats don't move, and nobody sits on the floor.
 *
 * After one round of these rules, every seat in the example layout becomes occupied:
 *
 * #.##.##.##
 * #######.##
 * #.#.#..#..
 * ####.##.##
 * #.##.##.##
 * #.#####.##
 * ..#.#.....
 * ##########
 * #.######.#
 * #.#####.##
 * After a second round, the seats with four or more occupied adjacent seats become empty again:
 *
 * #.LL.L#.##
 * #LLLLLL.L#
 * L.L.L..L..
 * #LLL.LL.L#
 * #.LL.LL.LL
 * #.LLLL#.##
 * ..L.L.....
 * #LLLLLLLL#
 * #.LLLLLL.L
 * #.#LLLL.##
 * This process continues for three more rounds:
 *
 * #.##.L#.##
 * #L###LL.L#
 * L.#.#..#..
 * #L##.##.L#
 * #.##.LL.LL
 * #.###L#.##
 * ..#.#.....
 * #L######L#
 * #.LL###L.L
 * #.#L###.##
 * #.#L.L#.##
 * #LLL#LL.L#
 * L.L.L..#..
 * #LLL.##.L#
 * #.LL.LL.LL
 * #.LL#L#.##
 * ..L.L.....
 * #L#LLLL#L#
 * #.LLLLLL.L
 * #.#L#L#.##
 * #.#L.L#.##
 * #LLL#LL.L#
 * L.#.L..#..
 * #L##.##.L#
 * #.#L.LL.LL
 * #.#L#L#.##
 * ..L.L.....
 * #L#L##L#L#
 * #.LLLLLL.L
 * #.#L#L#.##
 * At this point, something interesting happens: the chaos stabilizes and further applications of these rules cause
 * no seats to change state! Once people stop moving around, you count 37 occupied seats.
 *
 * Simulate your seating area by applying the seating rules repeatedly until no seats change state. How many seats
 * end up occupied?
 *
 * Your puzzle answer was 2344.
 *
 * --- Part Two ---
 * As soon as people start to arrive, you realize your mistake. People don't just care about adjacent seats - they
 * care about the first seat they can see in each of those eight directions!
 *
 * Now, instead of considering just the eight immediately adjacent seats, consider the first seat in each of those
 * eight directions. For example, the empty seat below would see eight occupied seats:
 *
 * .......#.
 * ...#.....
 * .#.......
 * .........
 * ..#L....#
 * ....#....
 * .........
 * #........
 * ...#.....
 * The leftmost empty seat below would only see one empty seat, but cannot see any of the occupied ones:
 *
 * .............
 * .L.L.#.#.#.#.
 * .............
 * The empty seat below would see no occupied seats:
 *
 * .##.##.
 * #.#.#.#
 * ##...##
 * ...L...
 * ##...##
 * #.#.#.#
 * .##.##.
 * Also, people seem to be more tolerant than you expected: it now takes five or more visible occupied seats for an
 * occupied seat to become empty (rather than four or more from the previous rules). The other rules still apply:
 * empty seats that see no occupied seats become occupied, seats matching no rule don't change, and floor never changes.
 *
 * Given the same starting layout as above, these new rules cause the seating area to shift around as follows:
 *
 * L.LL.LL.LL
 * LLLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLLL
 * L.LLLLLL.L
 * L.LLLLL.LL
 * #.##.##.##
 * #######.##
 * #.#.#..#..
 * ####.##.##
 * #.##.##.##
 * #.#####.##
 * ..#.#.....
 * ##########
 * #.######.#
 * #.#####.##
 * #.LL.LL.L#
 * #LLLLLL.LL
 * L.L.L..L..
 * LLLL.LL.LL
 * L.LL.LL.LL
 * L.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLL#
 * #.LLLLLL.L
 * #.LLLLL.L#
 * #.L#.##.L#
 * #L#####.LL
 * L.#.#..#..
 * ##L#.##.##
 * #.##.#L.##
 * #.#####.#L
 * ..#.#.....
 * LLL####LL#
 * #.L#####.L
 * #.L####.L#
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##LL.LL.L#
 * L.LL.LL.L#
 * #.LLLLL.LL
 * ..L.L.....
 * LLLLLLLLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##L#.#L.L#
 * L.L#.#L.L#
 * #.L####.LL
 * ..#.#.....
 * LLL###LLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 * #.L#.L#.L#
 * #LLLLLL.LL
 * L.L.L..#..
 * ##L#.#L.L#
 * L.L#.LL.L#
 * #.LLLL#.LL
 * ..#.L.....
 * LLL###LLL#
 * #.LLLLL#.L
 * #.L#LL#.L#
 * Again, at this point, people stop shifting around and the seating area reaches equilibrium. Once this occurs, you
 * count 26 occupied seats.
 *
 * Given the new visibility method and the rule change for occupied seats becoming empty, once equilibrium is
 * reached, how many seats end up occupied?
 *
 * Your puzzle answer was 2076.
 *
 * Both parts of this puzzle are complete! They provide two gold stars: **
 */

public class SeatingSystem {

    private Seat[][] layout;

    public int calculateOccupiedSeatNumWhenLayoutStopsChanging() {
        this.layout = processFile();
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

    public int calculateOccupiedSeatsWithNewVisibilityRules() {
        this.layout = processFile();
        Seat[][] nextRoundLayout = new Seat[layout.length][layout[0].length];
        while (!Arrays.deepEquals(nextRoundLayout, layout)) {
            Seat[][] tempLayout = layout;
            for (int i = 0; i < layout.length; i++) {
                for (int j = 0; j < layout[i].length; j++) {
                    nextRoundLayout[i][j] = layout[i][j].nextRoundNewRules(calculateOccupiedFirstVisibleSeats(i, j));
                }
            }
            layout = nextRoundLayout;
            nextRoundLayout = tempLayout;
        }
        return (int) Arrays.stream(layout).flatMap(Arrays::stream).filter(Seat::isOccupied).count();
    }

    private int calculateOccupiedFirstVisibleSeats(int i, int j) {
        int occupied = 0;
        for (Direction direction : Direction.values()) {
            occupied += calculateOccupiedSeatsInDirection(direction, i, j);
        }
        return occupied;
    }

    private int calculateOccupiedSeatsInDirection(Direction direction, int i, int j) {
        if (direction == Direction.UP) {
            if (i == 0) {
                return 0;
            } else if (layout[i - 1][j].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i - 1, j);
            } else {
                return layout[i - 1][j].isOccupied() ? 1 : 0;
            }
        } else if (direction == Direction.UP_LEFT) {
            if (i == 0 || j == 0) {
                return 0;
            } else if (layout[i - 1][j - 1].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i - 1, j - 1);
            } else {
                return layout[i - 1][j - 1].isOccupied() ? 1 : 0;
            }
        } else if (direction == Direction.UP_RIGHT) {
            if (i == 0 || j == layout[i].length -1) {
                return 0;
            } else if (layout[i - 1][j + 1].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i - 1, j + 1);
            } else {
                return layout[i - 1][j + 1].isOccupied() ? 1 : 0;
            }
        }  else if (direction == Direction.LEFT) {
            if (j == 0) {
                return 0;
            } else if (layout[i][j - 1].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i, j - 1);
            } else {
                return layout[i][j - 1].isOccupied() ? 1 : 0;
            }
        } else if (direction == Direction.RIGHT) {
            if (j == layout[i].length -1) {
                return 0;
            } else if (layout[i][j + 1].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i, j + 1);
            } else {
                return layout[i][j + 1].isOccupied() ? 1 : 0;
            }
        } else if (direction == Direction.DOWN) {
            if (i == layout.length -1) {
                return 0;
            } else if (layout[i + 1][j].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i + 1, j);
            } else {
                return layout[i + 1][j].isOccupied() ? 1 : 0;
            }
        } else if (direction == Direction.DOWN_LEFT) {
            if (i == layout.length -1 || j == 0) {
                return 0;
            } else if (layout[i + 1][j - 1].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i + 1, j - 1);
            } else {
                return layout[i + 1][j - 1].isOccupied() ? 1 : 0;
            }
        } else if (direction == Direction.DOWN_RIGHT) {
            if (i == layout.length -1 || j == layout[i].length - 1) {
                return 0;
            } else if (layout[i + 1][j + 1].getType() == Type.FLOOR) {
                return calculateOccupiedSeatsInDirection(direction, i + 1, j + 1);
            } else {
                return layout[i + 1][j + 1].isOccupied() ? 1 : 0;
            }
        }
        return 0;
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
        private Type type;

        public Seat(boolean isOccupied) {
            this.isOccupied = isOccupied;
        }

        public Seat() {
        }

        public boolean isOccupied() {
            return isOccupied;
        }

        public Type getType() {
            return Type.SEAT;
        }

        public Seat nextRound(int occupiedAdjacentSeats) {
            if (occupiedAdjacentSeats == 0) {
                return new Seat(true);
            } else if (occupiedAdjacentSeats >= 4) {
                return new Seat(false);
            }
            return this;
        }

        public Seat nextRoundNewRules(int occupiedAdjacentSeats) {
            if (occupiedAdjacentSeats == 0) {
                return new Seat(true);
            } else if (occupiedAdjacentSeats >= 5) {
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
        public Type getType() {
            return Type.FLOOR;
        }

        @Override
        public Seat nextRound(int occupiedAdjacentSeats) {
            return this;
        }

        @Override
        public Seat nextRoundNewRules(int occupiedAdjacentSeats) {
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

    public enum Type {
        FLOOR,
        SEAT
    }

    public enum Direction {
        UP,
        UP_LEFT,
        UP_RIGHT,
        LEFT,
        RIGHT,
        DOWN,
        DOWN_LEFT,
        DOWN_RIGHT
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
            return new Seat(true);
        } else if (c.equals('.')) {
            return new Floor();
        }
        throw new RuntimeException("Not able to parse unknown character: " + c);
    }
}
