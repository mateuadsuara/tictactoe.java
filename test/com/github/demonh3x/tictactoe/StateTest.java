package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class StateTest {
    private static final Player xPlayer = new Player();
    private static final Player oPlayer = new Player();

    private static final Player _ = null;
    private static final Player X = xPlayer;
    private static final Player O = oPlayer;

    private State createGameState(Player... pieces) {
        final State state = State.empty();
        return state
                .put(pieces[0], new Location(0, 0))
                .put(pieces[1], new Location(1, 0))
                .put(pieces[2], new Location(2, 0))
                .put(pieces[3], new Location(0, 1))
                .put(pieces[4], new Location(1, 1))
                .put(pieces[5], new Location(2, 1))
                .put(pieces[6], new Location(0, 2))
                .put(pieces[7], new Location(1, 2))
                .put(pieces[8], new Location(2, 2));
    }

    final State[] statesWithLineOfX = {
            createGameState(
                    X, X, X,
                    O, _, _,
                    O, _, _
            ),
            createGameState(
                    O, _, _,
                    X, X, X,
                    O, _, _
            ),
            createGameState(
                    O, _, _,
                    O, _, _,
                    X, X, X
            ),
            createGameState(
                    X, O, _,
                    X, O, _,
                    X, _, _
            ),
            createGameState(
                    O, X, _,
                    O, X, _,
                    _, X, _
            ),
            createGameState(
                    O, _, X,
                    O, _, X,
                    _, _, X
            ),
            createGameState(
                    O, _, X,
                    O, X, _,
                    X, _, _
            ),
            createGameState(
                    X, _, _,
                    O, X, _,
                    O, _, X
            )
    };

    public class LookAt {
        @Test
        public void anEmptyLocation_ReturnsNull() {
            final State state = createGameState(
                    _, X, X,
                    X, O, O,
                    O, O, X
            );
            assertThat(state.lookAt(new Location(0, 0)), is(_));
            assertThat(state.isEmptyAt(new Location(0, 0)), is(true));
        }

        @Test
        public void aLocationContainingAXPiece_ReturnsAXPiece() {
            final State state = createGameState(
                    _, _, _,
                    _, X, _,
                    _, _, _
            );
            assertThat(state.lookAt(new Location(1, 1)), is(X));
            assertThat(state.isEmptyAt(new Location(1, 1)), is(false));
        }

        @Test
        public void aLocationContainingAOPiece_ReturnsAOPiece() {
            final State state = createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, O
            );
            assertThat(state.lookAt(new Location(2, 2)), is(O));
            assertThat(state.isEmptyAt(new Location(2, 2)), is(false));
        }
    }

    public class PutPieceAtLocation {
        State originalState, newState;
        Location l;

        @Before
        public void setUp() {
            originalState = createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            );
            l = new Location(0, 0);
            newState = originalState.put(X, l);
        }

        @Test
        public void theOriginalGameState_shouldntHaveBeenModified() {
            assertThat(originalState.lookAt(l), is(_));
        }

        @Test
        public void theNewGameState_shouldHaveThePuttedPiece() {
            assertThat(newState.lookAt(l), is(X));
        }
    }

    public class IsNotFinished {
        private void assertIsNotFinished(State state) {
            assertThat(new Logic(state).isFinished(), is(false));
        }

        @Test
        public void givenAnEmptyGameState() {
            assertIsNotFinished(createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void givenAlmostFullGameWithoutAnyLine() {
            assertIsNotFinished(createGameState(
                    X, O, X,
                    O, _, O,
                    X, O, X
            ));
            assertIsNotFinished(createGameState(
                    X, _, X,
                    X, O, O,
                    O, X, O
            ));
        }
    }

    public class IsFinished {
        private void assertIsFinished(State... states) {
            for (State state : states)
                assertThat(new Logic(state).isFinished(), is(true));
        }

        @Test
        public void givenAFullGameStateWithoutLines() {
            assertIsFinished(createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            ));
        }

        @Test
        public void givenALine() {
            assertIsFinished(statesWithLineOfX);
        }
    }

    public class ThereIsNoWinner {
        private void assertNoWinner(State state) {
            Player[] players = {xPlayer, oPlayer};

            for (Player p : players)
                assertThat(new Logic(state).hasWon(p), is(false));
        }

        @Test
        public void givenAnEmptyGameState() {
            assertNoWinner(createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void givenAFullGameStateWithoutLines() {
            assertNoWinner(createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            ));
        }
    }

    public class ThereIsAWinner {
        private void assertTheWinner(Player p, State... states){
            for (State state : states)
                assertThat(new Logic(state).hasWon(p), is(true));
        }

        @Test
        public void givenALineOfX() {
            assertTheWinner(xPlayer, statesWithLineOfX);
        }

        @Test
        public void givenALineOfO() {
            assertTheWinner(oPlayer, createGameState(
                    O, O, O,
                    X, _, _,
                    X, _, _
            ));
        }
    }
}
