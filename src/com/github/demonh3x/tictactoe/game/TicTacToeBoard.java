package com.github.demonh3x.tictactoe.game;

import java.util.Arrays;
import java.util.List;

public class TicTacToeBoard implements Board{
    @Override
    public List<Location> getAllLocations() {
        return Arrays.asList(
                new Location(0, 0), new Location(1, 0), new Location(2, 0),
                new Location(0, 1), new Location(1, 1), new Location(2, 1),
                new Location(0, 2), new Location(1, 2), new Location(2, 2)
        );
    }
}