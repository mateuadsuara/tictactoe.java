package com.github.demonh3x.tictactoe.ai.interactors;

import com.github.demonh3x.tictactoe.game.Interactor;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.State;

import java.util.ArrayList;

public class FirstPossiblePlayInteractor implements Interactor {
    @Override
    public Location choose(State state) {
        final ArrayList<Location> available = new ArrayList<>();

        for (Location location : state.board.getAllLocations())
            if (state.isEmptyAt(location))
                available.add(location);

        return available.get(0);
    }
}
