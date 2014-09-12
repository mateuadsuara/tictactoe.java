package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.State;

import java.util.List;

public class CenterOption implements MoveOption {
    private final State state;

    public CenterOption(State state) {
        this.state = state;
    }

    private Results getAvailableCenters() {
        return new Results(state, getCenters()).available();
    }

    private List<Location> getCenters() {
        return state.board.getCenters();
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
