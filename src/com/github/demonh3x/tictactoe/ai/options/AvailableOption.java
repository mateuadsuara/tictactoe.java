package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.State;

import java.util.ArrayList;
import java.util.List;

public class AvailableOption implements MoveOption {
    private final State state;

    public AvailableOption(State state) {
        this.state = state;
    }

    @Override
    public boolean isAvailable() {
        return getAvailableLocations().isEmpty();
    }

    @Override
    public Location getLocation() {
        return getAvailableLocations().get(0);
    }

    private List<Location> getAvailableLocations() {
        final ArrayList<Location> available = new ArrayList<>();

        for (Location location : getAllLocations())
            if (state.isEmptyAt(location))
                available.add(location);

        return available;
    }

    private List<Location> getAllLocations() {
        return state.board.getAllLocations();
    }
}
