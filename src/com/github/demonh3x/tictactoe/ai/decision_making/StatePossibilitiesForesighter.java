package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.ArrayList;
import java.util.List;

public class StatePossibilitiesForesighter {
    private final Player playerPlayingNext;

    public StatePossibilitiesForesighter(Player playerPlayingNext) {
        this.playerPlayingNext = playerPlayingNext;
    }

    public List<State> foresee(State initial){
        return createStates(initial, playerPlayingNext, getAvailableLocations(initial));
    }

    private List<State> createStates(State initial, Player player, List<Location> locations) {
        final ArrayList<State> states = new ArrayList<>();

        for (Location location : locations){
            states.add(initial.put(player, location));
        }

        return states;
    }

    private List<Location> getAvailableLocations(State state) {
        final ArrayList<Location> available = new ArrayList<>();

        for (Location location : Location.getAll()){
            if (state.isEmptyAt(location))
                available.add(location);
        }

        return available;
    }
}
