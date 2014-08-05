package com.github.demonh3x.tictactoe;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        int index = 0;
        int x, y;
        Location l;

        do {
            x = index % Location.COLUMNS;
            y = index / Location.COLUMNS;
            l = new Location(x, y);

            index++;
        } while (!state.isEmptyAt(l));

        return l;
    }
}
