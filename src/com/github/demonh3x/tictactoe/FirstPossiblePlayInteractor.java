package com.github.demonh3x.tictactoe;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        for (Location l : Location.getAll())
            if (state.isEmptyAt(l))
                return l;

        throw new IllegalArgumentException();
    }
}
