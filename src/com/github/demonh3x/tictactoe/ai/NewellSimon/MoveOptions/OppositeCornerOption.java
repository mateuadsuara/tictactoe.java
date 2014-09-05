package com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.Results;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.ArrayList;
import java.util.List;

public class OppositeCornerOption implements MoveOption {
    private final State state;
    private final Player opponent;

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
        return new Results(state, Location.getCorners());
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
            oppositeLocations.add(location.opposite());
        }

        return oppositeLocations;
    }
}
