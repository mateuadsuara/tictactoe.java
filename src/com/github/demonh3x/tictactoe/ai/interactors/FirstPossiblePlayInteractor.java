package com.github.demonh3x.tictactoe.ai.interactors;

import com.github.demonh3x.tictactoe.ai.FirstPossibleMoveOption;
import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.ai.options.AvailableOption;
import com.github.demonh3x.tictactoe.game.*;

import java.util.Arrays;

public class FirstPossiblePlayInteractor implements Interactor {
    private final Player representedPlayer;

    public FirstPossiblePlayInteractor(Player representedPlayer){
        this.representedPlayer = representedPlayer;
    }

    @Override
    public Play play(State state) {
        return new Play(representedPlayer, getDecisionMaker(state).get());
    }

    private FirstPossibleMoveOption getDecisionMaker(State state) {
        return new FirstPossibleMoveOption(Arrays.<MoveOption>asList(
                new AvailableOption(state)
        ));
    }
}
