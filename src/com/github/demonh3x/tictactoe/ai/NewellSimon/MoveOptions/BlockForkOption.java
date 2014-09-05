package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class BlockForkOption implements MoveOption {
    private final Results opponentForkLocations;
    private final Player player;
    private final Player opponent;
    private Results availableLocations;

    public BlockForkOption(State state, Player player, Player opponent) {
        this.player = player;
        this.opponent = opponent;
        availableLocations = new Results(state, Location.getAll()).available();
        opponentForkLocations = availableLocations.forkableBy(opponent);
    }

    @Override
    public boolean isAvailable() {
        return opponentForkLocations.exist();
    }

    @Override
    public Location getLocation() {
        return availableLocations.forkBlockableBy(player, opponent).first();
    }
}
