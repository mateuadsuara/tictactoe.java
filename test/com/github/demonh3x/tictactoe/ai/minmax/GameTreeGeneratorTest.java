package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameTreeGeneratorTest {
    private static final Player X = new Player();
    private static final Player O = new Player();
    private static final Player _ = null;

    private void assertGameTree(State state, GameTree tree) {
        GameTreeGenerator generator = new GameTreeGenerator(X, O);
        assertThat(generator.generate(state, X), is(tree));
    }

    @Test
    public void GivenAWonState_ShouldGenerateALeafNodeWithTheWinner() {
        assertGameTree(
                StateLiteral.create(
                        X, X, X,
                        O, O, _,
                        _, _, _
                ),
                new WinningOutcome(X)
        );

        assertGameTree(
                StateLiteral.create(
                        X, X, _,
                        O, O, O,
                        X, _, _
                ),
                new WinningOutcome(O)
        );
    }

    @Test
    public void GivenADrawState_ShouldGenerateALeafNodeWithoutAWinner() {
        assertGameTree(
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, X, O
                ),
                DrawOutcome.get()
        );
    }

    @Test
    public void GivenAStateWithOnlyOnePlayAvailable_ShouldGenerateAOneLevelTree() {
        assertGameTree(
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, _, O
                ),
                new Choice(X, new Location(2, 1), DrawOutcome.get())
        );
    }
}
