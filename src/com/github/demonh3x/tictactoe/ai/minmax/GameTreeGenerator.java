package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class GameTreeGenerator {
    private final Player[] players;

    public GameTreeGenerator(Player... players) {
        this.players = players;
    }

    public GameTree generate(State initialState) {
        Logic logic = new Logic(initialState);
        return logic.isFinished() ?
                determineOutcome(logic) :
                determineChoices(initialState);
    }

    private Choice determineChoices(State initialState) {
        for (Location l : initialState.board.getAllLocations()) {
            if (initialState.isEmptyAt(l)) {
                GameTree subTree = generate(initialState.play(l));
                return new Choice(initialState.decisionMaker, l, subTree);
            }
        }

        throw new NotImplementedException();
    }

    private GameTree determineOutcome(Logic logic) {
        for (Player p : players)
            if (logic.hasWon(p))
                return new WinningOutcome(p);

        return DrawOutcome.get();
    }
}
