package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class GameStateTest {
    public class GivenAnEmptyGameState {
        private final GameState emptyGameState;

        public GivenAnEmptyGameState() {
            emptyGameState = new GameState();
        }

        @Test
        public void ThereIsNoWinner() {
            assertThat(emptyGameState.hasAWinner(), is(false));
        }

        @Test
        public void IsNotFinished() {
            assertThat(emptyGameState.isFinished(), is(false));
        }
    }
}
