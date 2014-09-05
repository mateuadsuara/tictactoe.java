package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.State;

public class AvailableOption implements MoveOption {
    private final State state;

    public AvailableOption(State state) {
        this.state = state;
    }

    @Override
    public boolean isAvailable() {
        return getAvailableLocations().exist();
    }

    @Override
    public Location getLocation() {
        return getAvailableLocations().first();
    }

    public Results getAvailableLocations() {
        return new Results(state, Location.getAll()).available();
    }
}
