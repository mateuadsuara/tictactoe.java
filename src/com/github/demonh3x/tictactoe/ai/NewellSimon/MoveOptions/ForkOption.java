package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class ForkOption implements MoveOption {
    private final State state;
    private final Player player;

    public ForkOption(State state, Player player) {
        this.state = state;
        this.player = player;
    }

    private Results getOwnForkLocations() {
        return new Results(state, Location.getAll()).available().forkableBy(player);
    }

    @Override
    public boolean isAvailable() {
        return getOwnForkLocations().exist();
    }

    @Override
    public Location getLocation() {
        return getOwnForkLocations().first();
    }
}