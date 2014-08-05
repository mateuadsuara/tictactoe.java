package com.github.demonh3x.tictactoe;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        int index = 0;
        int x;

        do {
            x = index;
            index++;
        } while (!state.isEmptyAt(new Location(x, 0)));

        return new Location(x, 0);
    }
}
