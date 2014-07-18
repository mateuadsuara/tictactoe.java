package com.github.demonh3x.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class GameTest {
    private Game g;

    @Before
    public void GivenANewGame() {
        g = new Game();
    }

    @Test
    public void ThereIsNoWinner() {
        assertThat(g.hasAWinner(), is(false));
    }

    @Test
    public void IsNotADraw() {
        assertThat(g.isADraw(), is(false));
    }
}
