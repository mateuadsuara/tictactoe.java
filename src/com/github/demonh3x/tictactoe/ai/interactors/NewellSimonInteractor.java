package com.github.demonh3x.tictactoe.ai.interactors;

import com.github.demonh3x.tictactoe.ai.options.*;
import com.github.demonh3x.tictactoe.game.*;

import java.util.Arrays;

public class NewellSimonInteractor implements Interactor {
    private final Player representedPlayer;
    private final Player opponent;

    public NewellSimonInteractor(Player representedPlayer, Player opponent) {
        this.representedPlayer = representedPlayer;
        this.opponent = opponent;
    }

    @Override
    public Location choose(State state) {
        return getDecisionMaker(state).getLocation();
    }

    private FirstPossibleMoveOption getDecisionMaker(State state) {
        return new FirstPossibleMoveOption(Arrays.asList(
                new WinOption(state, representedPlayer),
                new BlockOption(state, opponent),
                new ForkOption(state, representedPlayer),
                new BlockForkOption(state, representedPlayer, opponent),
                new CenterOption(state),
                new OppositeCornerOption(state, opponent),
                new CornerOption(state),
                new SideOption(state)
        ));
    }
}
