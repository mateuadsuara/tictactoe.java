package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class GameTest {
    public class GivenANewGame {
        private Game newGame;

        public GivenANewGame() {
            newGame = new Game();
        }

        @Test
        public void ThereIsNoWinner() {
            assertThat(newGame.hasAWinner(), is(false));
        }

        @Test
        public void IsNotADraw() {
            assertThat(newGame.isADraw(), is(false));
        }
    }
}
