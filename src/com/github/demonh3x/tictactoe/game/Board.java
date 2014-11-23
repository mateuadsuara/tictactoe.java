package com.github.demonh3x.tictactoe.game;

import java.util.Set;

public interface Board {
    public Set<Location> getAllLocations();
    public Set<Set<Location>> getPossibleLines();
    public boolean contains(Location location);
}
