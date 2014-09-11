package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import com.github.demonh3x.tictactoe.game.TicTacToeBoard;

import java.util.List;

public class BlockForkOption implements MoveOption {
    private final State state;
    private final Player player;
    private final Player opponent;

    public BlockForkOption(State state, Player player, Player opponent) {
        this.state = state;
        this.player = player;
        this.opponent = opponent;
    }

    private Results getOpponentForkLocations() {
        return getAvailableLocations().forkableBy(opponent);
    }

    private Results getAvailableLocations() {
        return new Results(state, getAllLocations()).available();
    }

    private List<Location> getAllLocations() {
        return new TicTacToeBoard().getAllLocations();
    }

    @Override
    public boolean isAvailable() {
        return getOpponentForkLocations().exist();
    }

    @Override
    public Location getLocation() {
        return getAvailableLocations().forkBlockableBy(player, opponent).first();
    }
}
