package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class BlockOption implements MoveOption {
    private final State state;
    private final Player opponent;

    public BlockOption(State state, Player opponent) {
        this.state = state;
        this.opponent = opponent;
    }

    private Results getLosingLocations() {
        return new Results(state, Location.getAll()).available().winnableBy(opponent);
    }

    @Override
    public boolean isAvailable() {
        return getLosingLocations().exist();
    }

    @Override
    public Location getLocation() {
        return getLosingLocations().first();
    }
}