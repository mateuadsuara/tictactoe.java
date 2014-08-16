package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.game.*;

import java.util.Arrays;
import java.util.List;

public class HardestInteractor implements Interactor {
    private final Player representedPlayer;

    public HardestInteractor(Player representedPlayer) {
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
