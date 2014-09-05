package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.State;

public class SideOption implements MoveOption {
    private final State state;

    public SideOption(State state) {
        this.state = state;
    }

    @Override
    public boolean isAvailable() {
        return getAvailableSides().exist();
    }

    @Override
    public Location getLocation() {
        return getAvailableSides().first();
    }

    private Results getAvailableSides() {
        return new Results(state, Location.getSides()).available();
    }
}
