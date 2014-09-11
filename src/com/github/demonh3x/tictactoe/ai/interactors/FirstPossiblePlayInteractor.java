package com.github.demonh3x.tictactoe.ai.interactors;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.ai.options.AvailableOption;
import com.github.demonh3x.tictactoe.game.Interactor;
import com.github.demonh3x.tictactoe.game.Play;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class FirstPossiblePlayInteractor implements Interactor {
    private final Player representedPlayer;

    public FirstPossiblePlayInteractor(Player representedPlayer){
        this.representedPlayer = representedPlayer;
    }

    @Override
    public Play play(State state) {
        return new Play(representedPlayer, getDecisionMaker(state).getLocation());
    }

    private MoveOption getDecisionMaker(State state) {
        return new AvailableOption(state);
    }
}
