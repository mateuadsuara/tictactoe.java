package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class GameStateTest {
    private static final Piece _ = null;
    private static final Piece X = new Piece();
    private static final Piece O = new Piece();

    private GameState createGameState(Piece... pieces) {
        return new GameState(Arrays.asList(pieces));
    }

    public class InvalidConstruction {
        @Test(expected = IllegalArgumentException.class)
        public void withNotEnoughPieces() {
            createGameState(
                    _, _, _,
                    _, _, _,
                    _, _
            );
        }

        @Test(expected = IllegalArgumentException.class)
        public void withTooManyPieces() {
            createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _,
                    _
            );
        }
    }

    public class IsNotFinished {
        private void assertIsNotFinished(GameState gs) {
            assertThat(gs.isFinished(), is(false));
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
        private void assertIsFinished(GameState gs) {
            assertThat(gs.isFinished(), is(true));
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
            assertIsFinished(createGameState(
                    X, X, X,
                    O, _, _,
                    O, _, _
            ));
            assertIsFinished(createGameState(
                    O, _, _,
                    X, X, X,
                    O, _, _
            ));
            assertIsFinished(createGameState(
                    O, _, _,
                    O, _, _,
                    X, X, X
            ));
            assertIsFinished(createGameState(
                    X, O, _,
                    X, O, _,
                    X, _, _
            ));
            assertIsFinished(createGameState(
                    O, X, _,
                    O, X, _,
                    _, X, _
            ));
            assertIsFinished(createGameState(
                    O, _, X,
                    O, _, X,
                    _, _, X
            ));
            assertIsFinished(createGameState(
                    O, _, X,
                    O, X, _,
                    X, _, _
            ));
            assertIsFinished(createGameState(
                    X, _, _,
                    O, X, _,
                    O, _, X
            ));
        }
    }

    public class ThereIsNoWinner {
        private void assertNoWinner(GameState gs) {
            assertThat(gs.getWinner(), is(nullValue()));
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
}
