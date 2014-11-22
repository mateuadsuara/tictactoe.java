package com.github.demonh3x.tictactoe.game;

import java.util.Arrays;
import java.util.List;

public class TicTacToeBoard implements Board{
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;

    @Override
    public List<Location> getAllLocations() {
        return Arrays.asList(
                new Location(0, 0), new Location(1, 0), new Location(2, 0),
                new Location(0, 1), new Location(1, 1), new Location(2, 1),
                new Location(0, 2), new Location(1, 2), new Location(2, 2)
        );
    }

    @Override
    public List<List<Location>> getPossibleLines() {
        return Arrays.asList(
                Arrays.asList(new Location(0, 0), new Location(1, 0), new Location(2, 0)),
                Arrays.asList(new Location(0, 1), new Location(1, 1), new Location(2, 1)),
                Arrays.asList(new Location(0, 2), new Location(1, 2), new Location(2, 2)),

                Arrays.asList(new Location(0, 0), new Location(0, 1), new Location(0, 2)),
                Arrays.asList(new Location(1, 0), new Location(1, 1), new Location(1, 2)),
                Arrays.asList(new Location(2, 0), new Location(2, 1), new Location(2, 2)),

                Arrays.asList(new Location(0, 0), new Location(1, 1), new Location(2, 2)),
                Arrays.asList(new Location(2, 0), new Location(1, 1), new Location(0, 2))
        );
    }

    @Override
    public List<Location> getCenters() {
        return Arrays.asList(new Location(1, 1));
    }

    public List<Location> getCorners() {
        return Arrays.asList(new Location(0, 0), new Location(2, 2), new Location(0, 2), new Location(2, 0));
    }

    public List<Location> getSides() {
        return Arrays.asList(new Location(1, 0), new Location(1, 2), new Location(0, 1), new Location(2, 1));
    }

    @Override
    public Location opposite(Location location) {
        final int maxX = COLUMNS - 1;
        final int oppositeX = Math.abs(location.col - maxX);
        final int maxY = ROWS - 1;
        final int oppositeY = Math.abs(location.row - maxY);
        return new Location(oppositeX, oppositeY);
    }

    @Override
    public boolean contains(Location location) {
        return location.col < COLUMNS && location.col >= 0 && location.row < ROWS && location.row >= 0;
    }
}
