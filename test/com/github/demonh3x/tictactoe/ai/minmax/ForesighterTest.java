package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.StateLiteral;
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

    private static void assertFutures(Player current, State initial, List<State> expectedFutures) {
        List<State> actualFutures = foreseeFutures(current, initial);
        assertThat(new HashSet<>(actualFutures), is(new HashSet<>(expectedFutures)));
    }

    private static List<State> foreseeFutures(Player current, State initial) {
        ArrayList<State> actualList = new ArrayList<>();

        for (Play p : new Foresighter(current, initial))
            actualList.add(initial.put(p.player, p.location));

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
    public void aStateWithOnlyOnePossibleFuture() {
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
        assertFutures(
                X,
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, _, O
                ),
                Arrays.asList(
                        StateLiteral.create(
                                X, X, O,
                                O, O, X,
                                X, X, O
                        )
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
                        StateLiteral.create(
                                X, X, O,
                                O, O, X,
                                _, X, _
                        ),
                        StateLiteral.create(
                                X, X, O,
                                O, O, X,
                                X, _, _
                        ),
                        StateLiteral.create(
                                X, X, O,
                                O, O, X,
                                _, _, X
                        )
                )
        );
    }
}
