package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class GameStateTest {
    private static final Piece _ = null;
    private static final Piece X = new Piece();
    private static final Piece O = new Piece();

    private GameState createGameState(Piece... pieces) {
        return new GameState(pieces);
    }

    public class IsFinished {
        private void assertIsNotFinished(GameState gs) {
            assertThat(gs.isFinished(), is(false));
        }
        private void assertIsFinished(GameState gs) {
            assertThat(gs.isFinished(), is(true));
        }

        @Test
        public void GivenAnEmptyGameState_IsNot() {
            assertIsNotFinished(createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void GivenAFullGameStateWitoutLines_Is() {
            assertIsFinished(createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            ));
        }

        @Test
        public void GivenALine_Is() {
            assertIsFinished(createGameState(
                    _, _, _,
                    O, _, O,
                    X, X, X
            ));
        }
    }
}
