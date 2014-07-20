package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

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

    public class IsNotFinished {
        private void assertIsNotFinished(GameState gs) {
            assertThat(gs.isFinished(), is(false));
        }

        @Test
        public void GivenAnEmptyGameState() {
            assertIsNotFinished(createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void GivenSomePiecesWithoutAnyLine() {
            assertIsNotFinished(createGameState(
                    _, _, _,
                    _, X, O,
                    X, O, X
            ));
        }
    }

    public class IsFinished {
        private void assertIsFinished(GameState gs) {
            assertThat(gs.isFinished(), is(true));
        }

        @Test
        public void GivenAFullGameStateWithoutLines() {
            assertIsFinished(createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            ));
        }

        @Test
        public void GivenALine() {
            assertIsFinished(createGameState(
                    _, _, _,
                    O, _, O,
                    X, X, X
            ));
        }
    }
}
