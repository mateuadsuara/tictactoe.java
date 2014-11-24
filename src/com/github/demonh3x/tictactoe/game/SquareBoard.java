package com.github.demonh3x.tictactoe.game;

import java.util.HashSet;
import java.util.Set;

public class SquareBoard implements Board {
    private final int sideSize;

    public SquareBoard(int sideSize) {
        this.sideSize = sideSize;
    }

    private Set<Location> locations = null;
    @Override
    public Set<Location> getAllLocations() {
        if (locations == null)
            locations = createAllLocations();

        return locations;
    }

    private Set<Location> createAllLocations() {
        final HashSet<Location> locations = new HashSet<>();

        for (int x = 0; x < sideSize; x++)
            for (int y = 0; y < sideSize; y++)
                locations.add(new Location(x, y));

        return locations;
    }

    private Set<Set<Location>> lines = null;
    @Override
    public Set<Set<Location>> getPossibleLines() {
        if (lines == null)
            lines = createLines();

        return lines;
    }

    private Set<Set<Location>> createLines() {
        Set<Set<Location>> lines = new HashSet<>();

        for (int col = 0; col < sideSize; col++) {
            for (int row = 0; row < sideSize; row++) {
                lines.add(getHorizontalLineFrom(col, row));
                lines.add(getVerticalLineFrom(col, row));
                lines.add(getDescendingDiagonalLineFrom(col, row));
                lines.add(getAscendingDiagonalLineFrom(col, row));
            }
        }
        lines.remove(null);

        return lines;
    }

    private Set<Location> getAscendingDiagonalLineFrom(int col, int row) {
        if (row > 0 || col > 0) return null;

        Set<Location> line = new HashSet<>();
        for (int i = 0; i < sideSize; i++){
            line.add(new Location(i, i));
        }
        return line;
    }

    private Set<Location> getDescendingDiagonalLineFrom(int col, int row) {
        if (row < sideSize -1 || col > 0) return null;

        Set<Location> line = new HashSet<>();
        for (int i = 0; i < sideSize; i++){
            line.add(new Location(i, (sideSize-1) -i));
        }
        return line;
    }

    private Set<Location> getVerticalLineFrom(int col, int row) {
        if (row > 0) return null;

        Set<Location> line = new HashSet<>();
        for (int r = 0; r < sideSize; r++){
            line.add(new Location(col, r));
        }
        return line;
    }

    private Set<Location> getHorizontalLineFrom(int col, int row) {
        if (col > 0) return null;

        Set<Location> line = new HashSet<>();
        for (int c = 0; c < sideSize; c++){
            line.add(new Location(c, row));
        }
        return line;
    }

    @Override
    public boolean contains(Location location) {
        return location.col >= 0 && location.col < sideSize
                && location.row >= 0 && location.row < sideSize;
    }
}
