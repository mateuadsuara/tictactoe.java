package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.game.*;
import org.junit.Test;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TextRendererTest {
    @Test
    public void emptyTicTacToeBoard() {
        final TextRenderer renderer = new TextRenderer(new HashMap<Player, Character>());
        final State state = State.empty(new TicTacToeBoard());
        assertThat(renderer.render(state), is(
                "   x 0   1   2\n" +
                " y +---+---+---+\n" +
                " 0 |   |   |   |\n" +
                "   +---+---+---+\n" +
                " 1 |   |   |   |\n" +
                "   +---+---+---+\n" +
                " 2 |   |   |   |\n" +
                "   +---+---+---+"
        ));
    }

    @Test
    public void somePlaysInTicTacToeBoard() {
        final Player X = new Player();
        final Player O = new Player();

        final HashMap<Player, Character> mappings = new HashMap<>();
        mappings.put(X, 'X');
        mappings.put(O, 'O');

        final TextRenderer renderer = new TextRenderer(mappings);

        State state = State.empty(new TicTacToeBoard());
        state = state.put(X, new Location(0, 0));
        state = state.put(O, new Location(1, 0));

        assertThat(renderer.render(state), is(
                "   x 0   1   2\n" +
                " y +---+---+---+\n" +
                " 0 | X | O |   |\n" +
                "   +---+---+---+\n" +
                " 1 |   |   |   |\n" +
                "   +---+---+---+\n" +
                " 2 |   |   |   |\n" +
                "   +---+---+---+"
        ));
    }

    private Board createBoard(final List<Location> availableLocations) {
        return new Board() {
            @Override
            public List<Location> getAllLocations() {
                return availableLocations;
            }

            @Override
            public List<List<Location>> getPossibleLines() {
                throw new NotImplementedException();
            }

            @Override
            public List<Location> getCenters() {
                throw new NotImplementedException();
            }

            @Override
            public List<Location> getCorners() {
                throw new NotImplementedException();
            }

            @Override
            public List<Location> getSides() {
                throw new NotImplementedException();
            }

            @Override
            public Location opposite(Location location) {
                throw new NotImplementedException();
            }

            @Override
            public boolean contains(Location location) {
                return availableLocations.contains(location);
            }
        };
    }

    private void assertEmptyBoardOfLocations(List<Location> locations, String expectedRender) {
        final TextRenderer renderer = new TextRenderer(new HashMap<Player, Character>());
        final Board oneLocationBoard = createBoard(locations);
        final State state = State.empty(oneLocationBoard);
        assertThat(renderer.render(state), is(expectedRender));
    }

    @Test
    public void noLocationsBoard() {
        assertEmptyBoardOfLocations(
                Arrays.<Location>asList(),
                ""
        );
    }

    @Test
    public void emptyOneLocationBoard() {
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(0, 0)
                ),
                "   x 0\n" +
                " y +---+\n" +
                " 0 |   |\n" +
                "   +---+"
        );
    }

    @Test
    public void emptyTwoVerticalLocationsBoard() {
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(0, 0), new Location(0, 1)
                ),
                "   x 0\n" +
                " y +---+\n" +
                " 0 |   |\n" +
                "   +---+\n" +
                " 1 |   |\n" +
                "   +---+"
        );
    }

    @Test
    public void emptyFourVerticalLocationsBoard() {
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(0, 0), new Location(0, 1), new Location(0, 2), new Location(0, 3)
                ),
                "   x 0\n" +
                " y +---+\n" +
                " 0 |   |\n" +
                "   +---+\n" +
                " 1 |   |\n" +
                "   +---+\n" +
                " 2 |   |\n" +
                "   +---+\n" +
                " 3 |   |\n" +
                "   +---+"
        );
    }

    @Test
    public void emptyTwoHorizontalLocationsBoard() {
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(0, 0), new Location(1, 0)
                ),
                "   x 0   1\n" +
                " y +---+---+\n" +
                " 0 |   |   |\n" +
                "   +---+---+"
        );
    }

    @Test
    public void emptyFourHorizontalLocationsBoard() {
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(0, 0), new Location(1, 0), new Location(2, 0), new Location(3, 0)
                ),
                "   x 0   1   2   3\n" +
                " y +---+---+---+---+\n" +
                " 0 |   |   |   |   |\n" +
                "   +---+---+---+---+"
        );
    }

    @Test
    public void emptyOneLocationBoardNotStartingBy0() {
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(1, 0)
                ),
                "   x 1\n" +
                " y +---+\n" +
                " 0 |   |\n" +
                "   +---+"
        );
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(2, 5)
                ),
                "   x 2\n" +
                " y +---+\n" +
                " 5 |   |\n" +
                "   +---+"
        );
    }

    @Test
    public void emptyBoardWithUnsortedLocations() {
        assertEmptyBoardOfLocations(
                Arrays.asList(
                        new Location(1, 1), new Location(0, 1), new Location(0, 0), new Location(1, 0)
                ),
                "   x 0   1\n" +
                " y +---+---+\n" +
                " 0 |   |   |\n" +
                "   +---+---+\n" +
                " 1 |   |   |\n" +
                "   +---+---+"
        );
    }
}
