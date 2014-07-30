package com.github.demonh3x.tictactoe;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
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
}
