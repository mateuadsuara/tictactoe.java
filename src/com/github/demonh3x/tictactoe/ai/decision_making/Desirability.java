package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class Desirability {
    private final Player playerDesiredToWin;

    public Desirability(Player playerDesiredToWin) {
        this.playerDesiredToWin = playerDesiredToWin;
    }

    public Float of(State state) {
        if (new Logic(state).hasWon(playerDesiredToWin))
            return 1f;

        return 0f;
    }
}
