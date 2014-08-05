package com.github.demonh3x.tictactoe;

import java.util.Arrays;

public class Location {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    public static Iterable<Location> getAll(){
        return Arrays.asList(
                new Location(0, 0), new Location(0, 1), new Location(0, 2),
                new Location(1, 0), new Location(1, 1), new Location(1, 2),
                new Location(2, 0), new Location(2, 1), new Location(2, 2)
        );
    }

    public final int x;
    public final int y;

    public Location(int x, int y) {
        if (x >= COLUMNS || x < 0 || y >= ROWS || y < 0) {
            throw new IllegalArgumentException(String.format("The location [x:%s, y:%s] is out of range!", x, y));
        }

        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location))
            return false;

        Location other = (Location) obj;
        return x == other.x && y == other.y;
    }
}
