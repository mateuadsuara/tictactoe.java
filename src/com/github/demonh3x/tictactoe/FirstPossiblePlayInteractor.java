package com.github.demonh3x.tictactoe;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        int index = 0;
        Location l;

        do {
            l = createLocation(index);
            index++;
        } while (!state.isEmptyAt(l));

        return l;
    }

    private Location createLocation(int index) {
        int x, y;
        x = index % Location.COLUMNS;
        y = index / Location.COLUMNS;
        return new Location(x, y);
    }
}
