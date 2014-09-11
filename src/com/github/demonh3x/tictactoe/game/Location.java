package com.github.demonh3x.tictactoe.game;

public class Location {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    public static String toString(int x, int y){
        return String.format("[x:%s, y:%s]", x, y);
    }

    public final int x;
    public final int y;

    public Location(int x, int y) {
        if (x >= COLUMNS || x < 0 || y >= ROWS || y < 0) {
            throw new IllegalArgumentException(String.format("The location %s is out of range!", toString(x, y)));
        }

        this.x = x;
        this.y = y;
    }

    public Location opposite() {
        final int maxX = Location.COLUMNS - 1;
        final int oppositeX = Math.abs(this.x - maxX);
        final int maxY = Location.ROWS - 1;
        final int oppositeY = Math.abs(this.y - maxY);
        return new Location(oppositeX, oppositeY);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location))
            return false;

        Location other = (Location) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public String toString() {
        return toString(x, y);
    }
}
