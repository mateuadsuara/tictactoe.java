package com.github.demonh3x.tictactoe;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        int index = 0;
        int x, y;

        do {
            x = index % Location.COLUMNS;
            y = index / Location.COLUMNS;

            index++;
        } while (!state.isEmptyAt(new Location(x, y)));

        return new Location(x, y);
    }
}
