package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class WinOption implements MoveOption {
    private final State state;
    private final Player player;

    public WinOption(State state, Player player) {
        this.state = state;
        this.player = player;
    }

    private Results getWinnableLocations() {
        return new Results(state, Location.getAll()).available().winnableBy(player);
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