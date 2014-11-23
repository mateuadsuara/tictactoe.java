package com.github.demonh3x.tictactoe.ai.interactors;

import com.github.demonh3x.tictactoe.ai.MoveOption;
import com.github.demonh3x.tictactoe.ai.options.AvailableOption;
import com.github.demonh3x.tictactoe.game.*;

public class FirstPossiblePlayInteractor implements Interactor {
    @Override
    public Location play(State state) {
        return getDecisionMaker(state).getLocation();
    }

    private MoveOption getDecisionMaker(State state) {
        return new AvailableOption(state);
    }
}
