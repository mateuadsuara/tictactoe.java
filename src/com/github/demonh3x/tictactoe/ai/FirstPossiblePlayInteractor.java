package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.GameInteractor;
import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        for (Location l : Location.getAll())
            if (state.isEmptyAt(l))
                return l;

        throw new IllegalArgumentException();
    }
}
