package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.State;

public class CornerOption implements MoveOption {
    private final State state;

    public CornerOption(State state) {
        this.state = state;
    }

    @Override
    public boolean isAvailable() {
        return getAvailableCorners().exist();
    }

    @Override
    public Location getLocation() {
        return getAvailableCorners().first();
    }

    private Results getAvailableCorners() {
        return new Results(state, Location.getCorners()).available();
    }
}