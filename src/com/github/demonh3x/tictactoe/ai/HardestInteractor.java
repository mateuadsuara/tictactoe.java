package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.game.*;

import java.util.ArrayList;
import java.util.List;

public class HardestInteractor implements Interactor {
    private final Player representedPlayer;

    public HardestInteractor(Player representedPlayer) {
        this.representedPlayer = representedPlayer;
    }

    @Override
    public Play play(State state) {
        return new Play(representedPlayer, new LocationDecision(representedPlayer, state).get());
    }

    private class LocationDecision {
        private final Player player;
        private final State state;

        public LocationDecision(Player player, State state) {
            this.player = player;
            this.state = state;
        }

        public Location get() {
            final List<Location> availableLocations = getAvailable();
            final List<Location> winningLocations = getWinning(availableLocations);

            if (winningLocations.isEmpty())
                return getFirst(availableLocations);
            else
                return getFirst(winningLocations);
        }

        private List<Location> getWinning(List<Location> locations) {
            final ArrayList<Location> winningLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(player, location);
                final Logic logic = new Logic(imaginaryState);
                if (logic.hasWon(player))
                    winningLocations.add(location);
            }

            return winningLocations;
        }

        private <T> T getFirst(List<T> list) {
            return list.get(0);
        }

        private List<Location> getAvailable() {
            final ArrayList<Location> available = new ArrayList<>();

            for (Location location : Location.getAll()){
                if (state.isEmptyAt(location))
                    available.add(location);
            }

            return available;
        }
    }
}
