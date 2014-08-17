package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

public class StatePossibilitiesForesighterTest {
    private static final Player X = new Player();
    private static final Player O = new Player();
    private static final Player _ = null;

    @Test
    public void GivenAFinishedState_ItHasNoPossibilities() {
        StatePossibilitiesForesighter foresighter = new StatePossibilitiesForesighter(O);
        State finishedState = StateLiteral.create(
                X, O, X,
                O, X, X,
                O, X, O
        );
        assertThat(foresighter.foresee(finishedState).size(), is(0));
    }

    @Test
    public void GivenAStateWithOnlyOnePlayRemaining_ItHasOnlyThePossibilityToFinishIt() {
        StatePossibilitiesForesighter foresighter = new StatePossibilitiesForesighter(X);
        State onlyOnePlayRemaining = StateLiteral.create(
                X, O, X,
                O, X, X,
                O, _, O
        );
        final List<State> possibilities = foresighter.foresee(onlyOnePlayRemaining);
        assertThat(possibilities.size(), is(1));
        assertThat(possibilities.get(0), is(StateLiteral.create(
                X, O, X,
                O, X, X,
                O, X, O
        )));
    }

    @Test
    public void GivenAStateWithTwoPlaysRemaining_ItHasTheTwoPossibleStates() {
        StatePossibilitiesForesighter foresighter = new StatePossibilitiesForesighter(O);
        State onlyOnePlayRemaining = StateLiteral.create(
                X, O, X,
                O, X, X,
                _, _, O
        );

        final List<State> possibilities = foresighter.foresee(onlyOnePlayRemaining);

        assertThat(possibilities.size(), is(2));
        assertThat(possibilities, hasItem(StateLiteral.create(
                X, O, X,
                O, X, X,
                O, _, O
        )));
        assertThat(possibilities, hasItem(StateLiteral.create(
                X, O, X,
                O, X, X,
                _, O, O
        )));
    }
}
