package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.*;

import java.util.ArrayList;
import java.util.List;

public class OppositeCornerOption implements MoveOption {
    private final State state;
    private final Player opponent;
    private final Board board = new TicTacToeBoard();

    public OppositeCornerOption(State state, Player opponent) {
        this.state = state;
        this.opponent = opponent;
    }

    private Results getAvailableOppositeCorners() {
        return new Results(state, opposite(getOpponentCorners())).available();
    }

    private Results getOpponentCorners() {
        return getCorners().occupiedBy(opponent);
    }

    private Results getCorners() {
        return new Results(state, board.getCorners());
    }

    @Override
    public boolean isAvailable() {
        return getAvailableOppositeCorners().exist();
    }

    @Override
    public Location getLocation() {
        return getAvailableOppositeCorners().first();
    }

    private List<Location> opposite(Iterable<Location> locations){
        final ArrayList<Location> oppositeLocations = new ArrayList<>();

        for (Location location : locations) {
            oppositeLocations.add(board.opposite(location));
        }

        return oppositeLocations;
    }
}
