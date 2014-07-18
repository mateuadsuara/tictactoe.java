package com.github.demonh3x.tictactoe;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class GameTest {
    @Test
    public void ThereIsNoWinner() {
        Game g = new Game();
        assertThat(g.hasAWinner(), is(false));
    }

    @Test
    public void IsNotADraw() {
        Game g = new Game();
        assertThat(g.isADraw(), is(false));
    }
}
