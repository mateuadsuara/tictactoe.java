package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.game.Location;

public interface MoveOption {
    public boolean isAvailable();
    public Location getLocation();
}
