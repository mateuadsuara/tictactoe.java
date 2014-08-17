package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class DesirabilityEvaluator {
    private final Player playerDesiredToWin;

    public DesirabilityEvaluator(Player playerDesiredToWin) {
        this.playerDesiredToWin = playerDesiredToWin;
    }

    public Float evaluate(State state) {
        if (new Logic(state).hasWon(playerDesiredToWin))
            return 1f;

        return 0f;
    }
}
