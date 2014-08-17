package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class Desirability {
    public class UnknownDesirabilityException extends RuntimeException {}

    private final Player playerDesiredToWin;
    private Player otherPlayer;

    public Desirability(Player playerDesiredToWin, Player otherPlayer) {
        this.playerDesiredToWin = playerDesiredToWin;
        this.otherPlayer = otherPlayer;
    }

    public Float of(State state) {
        final Logic logic = new Logic(state);

        if (logic.hasWon(playerDesiredToWin))
            return 1f;

        if (logic.hasWon(otherPlayer))
            return -1f;

        if (logic.isFinished())
            return 0f;

        throw new UnknownDesirabilityException();
    }
}
