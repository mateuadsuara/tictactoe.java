package com.github.demonh3x.tictactoe.game;

import java.util.HashSet;
import java.util.Set;

public class SquareBoard implements Board {
    private final int sideSize;

    public SquareBoard(int sideSize) {
        this.sideSize = sideSize;
    }

    @Override
    public Set<Location> getAllLocations() {
        final HashSet<Location> locations = new HashSet<>();

        for (int x = 0; x < sideSize; x++)
            for (int y = 0; y < sideSize; y++)
                locations.add(new Location(x, y));

        return locations;
    }

    @Override
    public Set<Set<Location>> getPossibleLines() {
        return null;
    }

    @Override
    public boolean contains(Location location) {
        return false;
    }
}
