package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class BlockOption implements MoveOption {
    private final Results losingLocations;

    public BlockOption(State state, Player opponent) {
        losingLocations = new Results(state, Location.getAll()).available().winnableBy(opponent);
    }

    @Override
    public boolean isAvailable() {
        return losingLocations.exist();
    }

    @Override
    public Location getLocation() {
        return losingLocations.first();
    }
}
