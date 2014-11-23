package com.github.demonh3x.tictactoe.game;

import java.util.Comparator;

public class Location {
    public static final Comparator<Location> COMPARATOR = new Comparator<Location>() {
        @Override
        public int compare(Location a, Location b) {
            if (a.row != b.row)
                return a.row - b.row;
            return a.col - b.col;
        }
    };
    
    public static String toString(int col, int row){
        return String.format("[col:%s, row:%s]", col, row);
    }

    public final int col;
    public final int row;

    public Location(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public int hashCode() {
        int result = col;
        result = 31 * result + row;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location))
            return false;

        Location other = (Location) obj;
        return col == other.col && row == other.row;
    }

    @Override
    public String toString() {
        return toString(col, row);
    }
}
