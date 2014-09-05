package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class ForkOption implements MoveOption {
    private final Results ownForkLocations;

    public ForkOption(State state, Player player) {
        ownForkLocations = new Results(state, Location.getAll()).available().forkableBy(player);
    }

    @Override
    public boolean isAvailable() {
        return ownForkLocations.exist();
    }

    @Override
    public Location getLocation() {
        return ownForkLocations.first();
    }
}
