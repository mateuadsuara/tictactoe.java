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

    public class GivenAnEmptyGameState {
        private final GameState emptyGameState;

        public GivenAnEmptyGameState() {
            emptyGameState = createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            );
        }

        @Test
        public void IsNotFinished() {
            assertThat(emptyGameState.isFinished(), is(false));
        }
    }

    public class GivenAFullGameStateWithNoLines {
        private final GameState fullGameStateWithNoLines;

        public GivenAFullGameStateWithNoLines() {
            fullGameStateWithNoLines = createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            );
        }

        @Test
        public void IsFinished() {
            assertThat(fullGameStateWithNoLines.isFinished(), is(true));
        }
    }
}
