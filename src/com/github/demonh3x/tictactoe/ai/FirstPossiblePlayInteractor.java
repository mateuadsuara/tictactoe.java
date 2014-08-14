package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.game.*;

public class FirstPossiblePlayInteractor implements Interactor {
    private final Player representedPlayer;

    public FirstPossiblePlayInteractor(Player representedPlayer){
        this.representedPlayer = representedPlayer;
    }

    @Override
    public Play play(State state) {
        for (Location l : Location.getAll())
            if (state.isEmptyAt(l))
                return new Play(representedPlayer, l);

        throw new IllegalArgumentException();
    }
}
