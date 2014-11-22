package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class GameTreeGenerator {
    private final Player[] players;

    public GameTreeGenerator(Player... players) {
        this.players = players;
    }

    public GameTree generate(State initialState) {
        Logic logic = new Logic(initialState);
        return logic.isFinished() ?
                determineOutcome(logic) :
                new Choice(initialState.decisionMaker, new Location(2, 1), DrawOutcome.get());
    }

    private GameTree determineOutcome(Logic logic) {
        for (Player p : players)
            if (logic.hasWon(p))
                return new WinningOutcome(p);

        return DrawOutcome.get();
    }
}
