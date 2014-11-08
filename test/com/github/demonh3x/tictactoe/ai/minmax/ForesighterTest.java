package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ForesighterTest {
    private static final Player X = new Player();
    private static final Player O = new Player();
    private static final Player _ = null;

    private static void assertFutures(Player current, State initial, List<State> expectedFutures) {
        assertThat(foreseeFutures(current, initial), is(expectedFutures));
    }

    private static List<State> foreseeFutures(Player current, State initial) {
        ArrayList<State> actualList = new ArrayList<>();

        for (State s : new Foresighter(current, initial))
            actualList.add(s);

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
                Arrays.<State>asList()
        );
    }

    @Test
    public void aStateWithOnlyOneOutcomeShouldHaveItAsFuture() {
        assertFutures(
                X,
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, O, _
                ),
                Arrays.asList(
                        StateLiteral.create(
                                X, X, O,
                                O, O, X,
                                X, O, X
                        )
                )
        );
    }
}
