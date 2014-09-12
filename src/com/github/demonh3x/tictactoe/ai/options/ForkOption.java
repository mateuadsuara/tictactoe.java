package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.List;

public class ForkOption implements MoveOption {
    private final State state;
    private final Player player;

    public ForkOption(State state, Player player) {
        this.state = state;
        this.player = player;
    }

    private Results getOwnForkLocations() {
        return new Results(state, getAllLocations()).available().forkableBy(player);
    }

    private List<Location> getAllLocations() {
        return state.board.getAllLocations();
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
