package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.game.*;

import java.util.ArrayList;
import java.util.List;

public class NewellSimonInteractor implements Interactor {
    private final Player representedPlayer;
    private final Player opponent;

    public NewellSimonInteractor(Player representedPlayer, Player opponent) {
        this.representedPlayer = representedPlayer;
        this.opponent = opponent;
    }

    @Override
    public Play play(State state) {
        return new Play(representedPlayer, new LocationDecision(representedPlayer, opponent, state).get());
    }

    private class LocationDecision {
        private final Player player;
        private final Player opponent;
        private final State state;

        public LocationDecision(Player player, Player opponent, State state) {
            this.player = player;
            this.opponent = opponent;
            this.state = state;
        }

        public Location get() {
            final List<Location> availableLocations = getAvailable(state, Location.getAll());

            final List<Location> winningLocations = getWinning(state, availableLocations);
            if (!winningLocations.isEmpty())
                return getFirst(winningLocations);

            final List<Location> losingLocations = getLosing(state, availableLocations);
            if (!losingLocations.isEmpty())
                return getFirst(losingLocations);

            final List<Location> forkLocations = getForks(state, availableLocations);
            if (!forkLocations.isEmpty())
                return getFirst(forkLocations);

            throw new RuntimeException("Unhandled possibility!");
        }

        private List<Location> getWinning(State state, Iterable<Location> locations) {
            final ArrayList<Location> winningLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(player, location);
                final Logic logic = new Logic(imaginaryState);
                if (logic.hasWon(player))
                    winningLocations.add(location);
            }

            return winningLocations;
        }

        private List<Location> getLosing(State state, Iterable<Location> locations) {
            final ArrayList<Location> losingLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(opponent, location);
                final Logic logic = new Logic(imaginaryState);
                if (logic.hasWon(opponent))
                    losingLocations.add(location);
            }

            return losingLocations;
        }

        private List<Location> getForks(State state, Iterable<Location> locations){
            final ArrayList<Location> forkLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(player, location);
                if (hasFork(imaginaryState))
                    forkLocations.add(location);
            }

            return forkLocations;
        }

        private boolean hasFork(State state) {
            return getWinning(state, getAvailable(state, Location.getAll())).size() > 1;
        }

        private <T> T getFirst(List<T> list) {
            return list.get(0);
        }

        private List<Location> getAvailable(State state, Iterable<Location> locations) {
            final ArrayList<Location> available = new ArrayList<>();

            for (Location location : locations){
                if (state.isEmptyAt(location))
                    available.add(location);
            }

            return available;
        }
    }
}
