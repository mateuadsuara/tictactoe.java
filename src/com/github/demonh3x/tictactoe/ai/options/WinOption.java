package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.List;

public class WinOption implements MoveOption {
    private final State state;
    private final Player player;

    public WinOption(State state, Player player) {
        this.state = state;
        this.player = player;
    }

    private Results getWinnableLocations() {
        return new Results(state, getAllLocations()).available().winnableBy(player);
    }

    private List<Location> getAllLocations() {
        return state.board.getAllLocations();
    }

    @Override
    public boolean isAvailable() {
        return getWinnableLocations().exist();
    }

    @Override
    public Location getLocation() {
        return getWinnableLocations().first();
    }
}