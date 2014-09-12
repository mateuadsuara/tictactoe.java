package com.github.demonh3x.tictactoe.game;

import java.util.List;

public interface Board {
    public List<Location> getAllLocations();
    public List<List<Location>> getPossibleLines();
    public List<Location> getCenters();
    public List<Location> getCorners();
    public List<Location> getSides();
    public Location opposite(Location location);
    public boolean contains(Location location);
}
