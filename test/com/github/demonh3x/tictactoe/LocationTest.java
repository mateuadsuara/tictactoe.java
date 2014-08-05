package com.github.demonh3x.tictactoe;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class LocationTest {
    private void assertIllegalLocation(int x, int y) {
        boolean exceptionThrown = false;
        try {
            new Location(x, y);
        } catch (IllegalArgumentException e){
            exceptionThrown = true;
        }

        final String reason = String.format(
                "Expected new Location(x:%s, y:%s) to throw IllegalArgumentException",
                x, y
        );
        assertThat(reason, exceptionThrown, is(true));
    }

    @Test
    public void outside_ThrowsIllegalArgumentException() {
        assertIllegalLocation(-1, 0);
        assertIllegalLocation(3, 2);
        assertIllegalLocation(3, 0);
        assertIllegalLocation(2, 3);
        assertIllegalLocation(0, -1);
    }

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
        assertThat(new Location(0, 0).toString(), is("[x:0, y:0]"));
        assertThat(new Location(1, 1).toString(), is("[x:1, y:1]"));
    }
}
