package com.github.demonh3x.tictactoe;

import org.junit.Test;

import static com.github.demonh3x.tictactoe.game.Player.O;
import static com.github.demonh3x.tictactoe.game.Player.X;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class PlayerTest {
    @Test
    public void XFollowsO() {
        assertThat(O.next(), is(X));
    }

    @Test
    public void OFollowsX() {
        assertThat(X.next(), is(O));
    }
}
