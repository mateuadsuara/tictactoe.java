package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class CenterOption implements MoveOption {
    private final State state;

    public CenterOption(State state) {
        this.state = state;
    }

    private Results getAvailableCenters() {
        return new Results(state, Location.getCenters()).available();
    }

    @Override
    public boolean isAvailable() {
        return getAvailableCenters().exist();
    }

    @Override
    public Location getLocation() {
        return getAvailableCenters().first();
    }
}
