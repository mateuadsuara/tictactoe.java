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

    @Test
    public void emptyOneLocationBoard() {
        final TextRenderer renderer = new TextRenderer(new HashMap<Player, Character>());
        final Board oneLocationBoard = new Board() {
            private List<Location> allLocations = Arrays.asList(new Location(0, 0));

            @Override
            public List<Location> getAllLocations() {
                return allLocations;
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
                return allLocations.contains(location);
            }
        };
        final State state = State.empty(oneLocationBoard);
        assertThat(renderer.render(state), is(
                "   x 0\n" +
                " y +---+\n" +
                " 0 |   |\n" +
                "   +---+"
        ));
    }
}
