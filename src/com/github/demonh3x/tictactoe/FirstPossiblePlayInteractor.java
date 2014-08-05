package com.github.demonh3x.tictactoe;

public class FirstPossiblePlayInteractor implements GameInteractor {
    @Override
    public Location play(GameState state) {
        final Location firstLocation = new Location(0, 0);

        if (state.isEmptyAt(firstLocation))
            return firstLocation;

        return new Location(1, 0);
    }
}
