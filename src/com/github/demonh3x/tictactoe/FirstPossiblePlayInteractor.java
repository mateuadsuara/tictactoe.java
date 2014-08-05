package com.github.demonh3x.tictactoe;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        int x = 0;

        if (!state.isEmptyAt(new Location(x, 0)))
            x++;

        return new Location(x, 0);
    }
}
