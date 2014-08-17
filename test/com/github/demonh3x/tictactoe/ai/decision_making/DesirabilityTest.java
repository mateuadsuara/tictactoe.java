package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DesirabilityTest {
    private static final Player X = new Player();
    private static final Player O = new Player();
    private static final Player _ = null;

    @Test
    public void ADrawStateIsNeitherPositiveNorNegative() {
        final State draw = StateLiteral.create(
                X, O, X,
                O, X, X,
                O, X, O
        );
        Desirability desirability = new Desirability(X);
        assertThat(desirability.of(draw), is(0.0f));
    }

    @Test
    public void AWinningStateIsTheMostPositive() {
        final State xHasWon = StateLiteral.create(
                X, O, X,
                _, X, O,
                O, _, X
        );
        Desirability desirability = new Desirability(X);
        assertThat(desirability.of(xHasWon), is(1.0f));
    }
}
