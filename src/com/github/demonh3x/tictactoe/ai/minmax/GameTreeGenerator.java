package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<Location, GameTree> subTrees = new HashMap<>();
        for (Location emptyLocation : findEmptyLocations(initialState)) {
            subTrees.put(emptyLocation, generate(initialState.play(emptyLocation)));
        }
        return new Choice(subTrees);
    }

    private List<Location> findEmptyLocations(State initialState) {
        List<Location> emptyLocations = new ArrayList<>();
        for (Location l : initialState.board.getAllLocations()) {
            if (initialState.isEmptyAt(l)) {
                emptyLocations.add(l);
            }
        }
        return emptyLocations;
    }

    private GameTree determineOutcome(Logic logic) {
        for (Player p : players)
            if (logic.hasWon(p))
                return WinningOutcome.get();

        return DrawOutcome.get();
    }
}
