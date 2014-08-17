package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DesirabilityEvaluatorTest {
    private static final Player X = new Player();
    private static final Player O = new Player();
    private static final Player _ = null;

    @Test
    public void ADrawStateIsNeitherPositiveNorNegative() {
        final State state = StateLiteral.create(
                X, O, X,
                O, X, X,
                O, X, O
        );
        DesirabilityEvaluator desirabilityEvaluator = new DesirabilityEvaluator();
        assertThat(desirabilityEvaluator.evaluate(state), is(0.0f));
    }
}
