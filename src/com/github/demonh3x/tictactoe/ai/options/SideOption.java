package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.State;
import com.github.demonh3x.tictactoe.game.TicTacToeBoard;

import java.util.List;

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
        return new Results(state, getSides()).available();
    }

    private List<Location> getSides() {
        return new TicTacToeBoard().getSides();
    }
}
