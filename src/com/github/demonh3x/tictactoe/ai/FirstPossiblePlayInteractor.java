package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.Interactor;
import com.github.demonh3x.tictactoe.State;
import com.github.demonh3x.tictactoe.Location;

public class FirstPossiblePlayInteractor implements Interactor {
    @Override
    public Location play(State state) {
        for (Location l : Location.getAll())
            if (state.isEmptyAt(l))
                return l;

        throw new IllegalArgumentException();
    }
}
