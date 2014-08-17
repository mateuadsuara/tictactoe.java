package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.core.Is.is;

public class StatePossibilitiesForesighterTest {
    private static final Player X = new Player();
    private static final Player O = new Player();
    private static final Player _ = null;

    private void assertPossibilities(Player playingNext, State initial, List<State> expectedPossibilities){
        final StatePossibilitiesForesighter foresighter = new StatePossibilitiesForesighter(playingNext);
        final List<State> actualPossibilities = foresighter.foresee(initial);

        assertThat(actualPossibilities.size(), is(expectedPossibilities.size()));
        for (State state : expectedPossibilities){
            assertThat(actualPossibilities, hasItem(state));
        }
    }

    @Test
    public void GivenAFinishedState_ItHasNoPossibilities() {
        State finishedState = StateLiteral.create(
                X, O, X,
                O, X, X,
                O, X, O
        );
        final List<State> noPossibilities = Arrays.<State>asList();
        assertPossibilities(O, finishedState, noPossibilities);
    }

    @Test
    public void GivenAStateWithOnlyOnePlayRemaining_ItHasOnlyThePossibilityToFinishIt() {
        State onlyOnePlayRemaining = StateLiteral.create(
                X, O, X,
                O, X, X,
                O, _, O
        );
        final State finishedState = StateLiteral.create(
                X, O, X,
                O, X, X,
                O, X, O
        );
        assertPossibilities(X, onlyOnePlayRemaining, Arrays.asList(finishedState));
    }

    @Test
    public void GivenAStateWithTwoPlaysRemaining_ItHasTheTwoPossibleStates() {
        final State twoPlaysRemaining = StateLiteral.create(
                X, O, X,
                O, X, X,
                _, _, O
        );
        final List<State> possibleStates = Arrays.asList(
                StateLiteral.create(
                        X, O, X,
                        O, X, X,
                        O, _, O
                ),
                StateLiteral.create(
                        X, O, X,
                        O, X, X,
                        _, O, O
                )
        );
        assertPossibilities(O, twoPlaysRemaining, possibleStates);
    }
}
