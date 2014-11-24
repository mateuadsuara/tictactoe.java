package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.Board;
import com.github.demonh3x.tictactoe.game.LiteralSet;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.SquareBoard;
import org.junit.Test;

import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SquareBoardTest {
    private void assertAllLocations(int sideSize, Set<Location> expectedLocations) {
        Board board = new SquareBoard(sideSize);
        assertThat(board.getAllLocations(), is(expectedLocations));
    }

    @Test
    public void aBoard1By1_has1Location() {
        assertAllLocations(1, LiteralSet.of(
                new Location(0, 0)
        ));
    }

    @Test
    public void aBoard2By2_has4Locations() {
        assertAllLocations(2, LiteralSet.of(
                new Location(0, 0), new Location(1, 0),
                new Location(0, 1), new Location(1, 1)
        ));
    }

    @Test
    public void aBoard3By3_has9Locations() {
        assertAllLocations(3, LiteralSet.of(
                new Location(0, 0), new Location(1, 0), new Location(2, 0),
                new Location(0, 1), new Location(1, 1), new Location(2, 1),
                new Location(0, 2), new Location(1, 2), new Location(2, 2)
        ));
    }

    private void assertLines(int sideSize, Set<Set<Location>> expectedLines) {
        Board board = new SquareBoard(sideSize);
        Set<Set<Location>> lines = board.getPossibleLines();
        assertThat(lines, is(expectedLines));
    }

    @Test
    public void aBoard2By2_has4PossibleLines() {
        assertLines(2, LiteralSet.of(
                LiteralSet.of(new Location(0, 0), new Location(1, 0)),
                LiteralSet.of(new Location(0, 1), new Location(1, 1)),

                LiteralSet.of(new Location(0, 0), new Location(0, 1)),
                LiteralSet.of(new Location(1, 0), new Location(1, 1)),

                LiteralSet.of(new Location(0, 0), new Location(1, 1)),
                LiteralSet.of(new Location(0, 1), new Location(1, 0))
        ));
    }

    @Test
    public void aBoard3By3_has8PossibleLines() {
        assertLines(3, LiteralSet.of(
                LiteralSet.of(new Location(0, 0), new Location(1, 0), new Location(2, 0)),
                LiteralSet.of(new Location(0, 1), new Location(1, 1), new Location(2, 1)),
                LiteralSet.of(new Location(0, 2), new Location(1, 2), new Location(2, 2)),

                LiteralSet.of(new Location(0, 0), new Location(0, 1), new Location(0, 2)),
                LiteralSet.of(new Location(1, 0), new Location(1, 1), new Location(1, 2)),
                LiteralSet.of(new Location(2, 0), new Location(2, 1), new Location(2, 2)),

                LiteralSet.of(new Location(0, 0), new Location(1, 1), new Location(2, 2)),
                LiteralSet.of(new Location(2, 0), new Location(1, 1), new Location(0, 2))
        ));
    }

    @Test
    public void aBoard2By2_containsTheLocationsInside() {
        Board board = new SquareBoard(2);
        assertTrue(board.contains(new Location(0, 0)));
        assertTrue(board.contains(new Location(1, 1)));
        assertFalse(board.contains(new Location(-1, 0)));
        assertFalse(board.contains(new Location(0, -1)));
        assertFalse(board.contains(new Location(2, 1)));
        assertFalse(board.contains(new Location(1, 2)));
    }

    @Test
    public void aBoard3By3_containsTheLocationsInside() {
        Board board = new SquareBoard(3);
        assertTrue(board.contains(new Location(0, 0)));
        assertTrue(board.contains(new Location(2, 2)));
        assertFalse(board.contains(new Location(-1, 0)));
        assertFalse(board.contains(new Location(0, -1)));
        assertFalse(board.contains(new Location(3, 2)));
        assertFalse(board.contains(new Location(2, 3)));
    }
}
