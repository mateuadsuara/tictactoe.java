package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class WinOption implements MoveOption {
    private final Results winnableLocations;

    public WinOption(State state, Player player) {
        winnableLocations = new Results(state, Location.getAll()).available().winnableBy(player);
    }

    @Override
    public boolean isAvailable() {
        return winnableLocations.exist();
    }

    @Override
    public Location getLocation() {
        return winnableLocations.first();
    }
}