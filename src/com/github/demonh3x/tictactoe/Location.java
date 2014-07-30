package com.github.demonh3x.tictactoe;

public class Location {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    public final int x;
    public final int y;

    public Location(int x, int y) {
        if (x >= COLUMNS || x < 0 || y >= ROWS || y < 0) {
            throw new IllegalArgumentException(String.format("The location [x:%s, y:%s] is out of range!", x, y));
        }

        this.x = x;
        this.y = y;
    }
}
