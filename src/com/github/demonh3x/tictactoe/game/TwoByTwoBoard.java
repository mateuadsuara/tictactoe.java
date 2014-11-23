package com.github.demonh3x.tictactoe.game;

import java.util.Set;

public class TwoByTwoBoard implements Board {
    private static final int COLUMNS = 2;
    private static final int ROWS = 2;

    @Override
    public Set<Location> getAllLocations() {
        return LiteralSet.of(
                new Location(1, 1), new Location(0, 1), new Location(0, 0), new Location(1, 0)
        );
    }

    @Override
    public Set<Set<Location>> getPossibleLines() {
        return LiteralSet.of(
                LiteralSet.of(new Location(0, 0), new Location(1, 0)),
                LiteralSet.of(new Location(0, 0), new Location(0, 1)),
                LiteralSet.of(new Location(0, 0), new Location(1, 1)),
                LiteralSet.of(new Location(0, 1), new Location(1, 1))
        );
    }

    @Override
    public boolean contains(Location location) {
        return location.col < COLUMNS && location.col >= 0 && location.row < ROWS && location.row >= 0;
    }
}
