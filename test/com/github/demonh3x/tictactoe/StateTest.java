package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.*;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class StateTest {
    private static final Player _ = null;
    private static final Player X = new Player();
    private static final Player O = new Player();

    final State[] statesWithLineOfX = {
            StateLiteral.create(
                    X, X, X,
                    O, _, _,
                    O, _, _
            ),
            StateLiteral.create(
                    O, _, _,
                    X, X, X,
                    O, _, _
            ),
            StateLiteral.create(
                    O, _, _,
                    O, _, _,
                    X, X, X
            ),
            StateLiteral.create(
                    X, O, _,
                    X, O, _,
                    X, _, _
            ),
            StateLiteral.create(
                    O, X, _,
                    O, X, _,
                    _, X, _
            ),
            StateLiteral.create(
                    O, _, X,
                    O, _, X,
                    _, _, X
            ),
            StateLiteral.create(
                    O, _, X,
                    O, X, _,
                    X, _, _
            ),
            StateLiteral.create(
                    X, _, _,
                    O, X, _,
                    O, _, X
            )
    };

    public class LookAt {
        @Test
        public void anEmptyLocation_ReturnsNull() {
            final State state = StateLiteral.create(
                    _, X, X,
                    X, O, O,
                    O, O, X
            );
            assertThat(state.lookAt(new Location(0, 0)), is(_));
            assertThat(state.isEmptyAt(new Location(0, 0)), is(true));
        }

        @Test
        public void aLocationContainingAXPiece_ReturnsAXPiece() {
            final State state = StateLiteral.create(
                    _, _, _,
                    _, X, _,
                    _, _, _
            );
            assertThat(state.lookAt(new Location(1, 1)), is(X));
            assertThat(state.isEmptyAt(new Location(1, 1)), is(false));
        }

        @Test
        public void aLocationContainingAOPiece_ReturnsAOPiece() {
            final State state = StateLiteral.create(
                    _, _, _,
                    _, _, _,
                    _, _, O
            );
            assertThat(state.lookAt(new Location(2, 2)), is(O));
            assertThat(state.isEmptyAt(new Location(2, 2)), is(false));
        }

        private void assertIllegalLocation(int x, int y) {
            final State state = StateLiteral.empty();

            boolean exceptionThrown = false;
            try {
                state.lookAt(new Location(x, y));
            } catch (IllegalArgumentException e){
                exceptionThrown = true;
            }

            final String reason = String.format(
                    "Expected state.lookAt(Location(x:%s, y:%s)) to throw IllegalArgumentException",
                    x, y
            );
            assertThat(reason, exceptionThrown, is(true));
        }

        @Test
        public void outside_ThrowsIllegalArgumentException() {
            assertIllegalLocation(-1, 0);
            assertIllegalLocation(3, 2);
            assertIllegalLocation(3, 0);
            assertIllegalLocation(2, 3);
            assertIllegalLocation(0, -1);
        }
    }

    public class PutPieceAtLocation {
        State originalState, newState;
        Location l;

        @Before
        public void setUp() {
            originalState = StateLiteral.create(
                    _, _, _,
                    _, _, _,
                    _, _, _
            );
            l = new Location(0, 0);
            newState = originalState.put(new Play(X, l));
        }

        @Test
        public void theOriginalGameState_shouldntHaveBeenModified() {
            assertThat(originalState.lookAt(l), is(_));
        }

        @Test
        public void theNewGameState_shouldHaveThePuttedPiece() {
            assertThat(newState.lookAt(l), is(X));
        }

        private void assertIllegalLocation(int x, int y) {
            final State state = StateLiteral.empty();

            boolean exceptionThrown = false;
            try {
                state.put(new Play(X, new Location(x, y)));
            } catch (IllegalArgumentException e){
                exceptionThrown = true;
            }

            final String reason = String.format(
                    "Expected state.put(Play(Player, Location(x:%s, y:%s))) to throw IllegalArgumentException",
                    x, y
            );
            assertThat(reason, exceptionThrown, is(true));
        }

        @Test
        public void outside_ThrowsIllegalArgumentException() {
            assertIllegalLocation(-1, 0);
            assertIllegalLocation(3, 2);
            assertIllegalLocation(3, 0);
            assertIllegalLocation(2, 3);
            assertIllegalLocation(0, -1);
        }
    }

    public class IsNotFinished {
        private void assertIsNotFinished(State state) {
            assertThat(new Logic(state).isFinished(), is(false));
        }

        @Test
        public void givenAnEmptyGameState() {
            assertIsNotFinished(StateLiteral.create(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void givenAlmostFullGameWithoutAnyLine() {
            assertIsNotFinished(StateLiteral.create(
                    X, O, X,
                    O, _, O,
                    X, O, X
            ));
            assertIsNotFinished(StateLiteral.create(
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
            assertIsFinished(StateLiteral.create(
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
            Player[] players = {X, O};

            for (Player p : players)
                assertThat(new Logic(state).hasWon(p), is(false));
        }

        @Test
        public void givenAnEmptyGameState() {
            assertNoWinner(StateLiteral.create(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void givenAFullGameStateWithoutLines() {
            assertNoWinner(StateLiteral.create(
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
            assertTheWinner(X, statesWithLineOfX);
        }

        @Test
        public void givenALineOfO() {
            assertTheWinner(O, StateLiteral.create(
                    O, O, O,
                    X, _, _,
                    X, _, _
            ));
        }
    }
}
