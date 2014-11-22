package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.Location;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class LocationTest {
    @Test
    public void notEqual() {
        assertThat(new Location(0, 0), is(not(new Location(0, 1))));
        assertThat(new Location(0, 0), is(not(new Location(1, 0))));
    }

    @Test
    public void equality() {
        assertThat(new Location(0, 0), is(new Location(0, 0)));
        assertThat(new Location(1, 1), is(new Location(1, 1)));
    }

    @Test
    public void stringRepresentation() {
        assertThat(new Location(0, 0).toString(), is("[col:0, row:0]"));
        assertThat(new Location(1, 1).toString(), is("[col:1, row:1]"));
    }
}
