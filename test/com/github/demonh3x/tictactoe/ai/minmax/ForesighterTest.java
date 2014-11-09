package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Play;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ForesighterTest {
    private static final Player X = new Player();
    private static final Player O = new Player();
    private static final Player _ = null;

    private static void assertFutures(Player current, State initial, List<Location> expectedFutures) {
        List<Play> actualFutures = foreseeFutures(current, initial);
        List<Play> expectedFuturePlays = createPlays(current, expectedFutures);
        assertThat(new HashSet<>(actualFutures), is(new HashSet<>(expectedFuturePlays)));
    }

    private static List<Play> createPlays(Player player, List<Location> locations) {
        ArrayList<Play> plays = new ArrayList<>();

        for (Location l : locations)
            plays.add(new Play(player, l));

        return plays;
    }

    private static List<Play> foreseeFutures(Player current, State initial) {
        ArrayList<Play> actualList = new ArrayList<>();

        for (Play p : new Foresighter(current, initial))
            actualList.add(p);

        return actualList;
    }

    @Test
    public void aFinishedStateHasNoFutures() {
        assertFutures(
                O,
                StateLiteral.create(
                        X, X, X,
                        O, O, X,
                        O, X, O
                ),
                Arrays.<Location>asList()
        );
    }

    @Test
    public void aStateWithOnlyOnePossibleFuture() {
        assertFutures(
                X,
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, O, _
                ),
                Arrays.asList(
                        new Location(2, 2)
                )
        );
        assertFutures(
                X,
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, _, O
                ),
                Arrays.asList(
                        new Location(1, 2)
                )
        );
    }

    @Test
    public void aStateWithThreePossibleFutures() {
        assertFutures(
                X,
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        _, _, _
                ),
                Arrays.asList(
                        new Location(1, 2),
                        new Location(0, 2),
                        new Location(2, 2)
                )
        );
    }
}
