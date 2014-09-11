package com.github.demonh3x.tictactoe.game;

public class Location {
    public static String toString(int x, int y){
        return String.format("[x:%s, y:%s]", x, y);
    }

    public final int x;
    public final int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
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
