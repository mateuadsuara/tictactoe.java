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
            final List<Location> availableLocations = getAvailable(Location.getAll());
            final List<Location> winningLocations = getWinning(availableLocations);
            final List<Location> losingLocations = getLosing(availableLocations);

            if (!winningLocations.isEmpty())
                return getFirst(winningLocations);

            if (!losingLocations.isEmpty())
                return getFirst(losingLocations);

            return getFirst(availableLocations);
        }

        private List<Location> getWinning(Iterable<Location> locations) {
            final ArrayList<Location> winningLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(player, location);
                final Logic logic = new Logic(imaginaryState);
                if (logic.hasWon(player))
                    winningLocations.add(location);
            }

            return winningLocations;
        }

        private List<Location> getLosing(Iterable<Location> locations) {
            final ArrayList<Location> losingLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(opponent, location);
                final Logic logic = new Logic(imaginaryState);
                if (logic.hasWon(opponent))
                    losingLocations.add(location);
            }

            return losingLocations;
        }

        private <T> T getFirst(List<T> list) {
            return list.get(0);
        }

        private List<Location> getAvailable(Iterable<Location> locations) {
            final ArrayList<Location> available = new ArrayList<>();

            for (Location location : locations){
                if (state.isEmptyAt(location))
                    available.add(location);
            }

            return available;
        }
    }
}
