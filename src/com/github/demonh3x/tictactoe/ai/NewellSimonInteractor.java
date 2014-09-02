package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.game.*;

import java.util.ArrayList;
import java.util.Arrays;
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

    private class StateAnalyser {
        private final State state;

        public StateAnalyser(State state){
            this.state = state;
        }

        private List<Location> getAvailableLocationsFrom(Iterable<Location> toLookAt) {
            final ArrayList<Location> available = new ArrayList<>();

            for (Location location : toLookAt){
                if (state.isEmptyAt(location))
                    available.add(location);
            }

            return available;
        }
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
            final List<Location> availableLocations = new StateAnalyser(state).getAvailableLocationsFrom(Location.getAll());

            final List<Location> winningLocations = getPossibleWinnings(state, player, availableLocations);
            if (!winningLocations.isEmpty())
                return getFirst(winningLocations);

            final List<Location> losingLocations = getPossibleWinnings(state, opponent, availableLocations);
            if (!losingLocations.isEmpty())
                return getFirst(losingLocations);

            final List<Location> forkLocations = getPossibleForks(state, player, availableLocations);
            if (!forkLocations.isEmpty())
                return getFirst(forkLocations);

            final List<Location> opponentForkLocations = getPossibleForks(state, opponent, availableLocations);
            if (!opponentForkLocations.isEmpty())
                return getFirst(getForkBlockingLocations(state, player, opponent, availableLocations));

            final Location center = new Location(1, 1);
            if (state.isEmptyAt(center))
                return center;

            final List<Location> corners = Arrays.asList(new Location(0, 0), new Location(2, 2), new Location(0, 2), new Location(2, 0));
            final List<Location> opponentCorners = getOccupiedBy(state, opponent, corners);
            final List<Location> availableOppositeCorners = new StateAnalyser(state).getAvailableLocationsFrom(opposite(opponentCorners));
            if (!availableOppositeCorners.isEmpty())
                return getFirst(availableOppositeCorners);

            final List<Location> availableCorners = new StateAnalyser(state).getAvailableLocationsFrom(corners);
            if (!availableCorners.isEmpty())
                return getFirst(availableCorners);

            final List<Location> sides = Arrays.asList(new Location(1, 0), new Location(1, 2), new Location(0, 1), new Location(2, 1));
            final List<Location> availableSides = new StateAnalyser(state).getAvailableLocationsFrom(sides);
            if (!availableSides.isEmpty())
                return getFirst(availableSides);

            throw new RuntimeException("Unhandled possibility!");
        }

        private Location opposite(Location location) {
            final int maxX = Location.COLUMNS - 1;
            final int oppositeX = Math.abs(location.x - maxX);
            final int maxY = Location.ROWS - 1;
            final int oppositeY = Math.abs(location.y - maxY);
            return new Location(oppositeX, oppositeY);
        }

        private List<Location> opposite(List<Location> locations){
            final ArrayList<Location> oppositeLocations = new ArrayList<>();

            for (Location location : locations) {
                oppositeLocations.add(opposite(location));
            }

            return oppositeLocations;
        }

        private List<Location> getOccupiedBy(State state, Player player, List<Location> locations) {
            final ArrayList<Location> occupiedByPlayer = new ArrayList<>();

            for (Location location : locations) {
                if (state.lookAt(location) == player)
                    occupiedByPlayer.add(location);
            }

            return occupiedByPlayer;
        }

        private List<Location> getForkBlockingLocations(State state, Player player, Player opponent, List<Location> locations) {
            final ArrayList<Location> forkBlockingLocations = new ArrayList<>();

            final List<Location> attackLocations = getAttackLocations(state, player, locations);

            for (Location attackLocation : attackLocations){
                final State imaginaryState = state.put(player, attackLocation);
                final List<Location> imaginaryAvailableLocations = removeFrom(attackLocations, Arrays.asList(attackLocation));
                final List<Location> imaginaryWinnings = getPossibleWinnings(imaginaryState, player, imaginaryAvailableLocations);
                for (Location defendingLocation : imaginaryWinnings) {
                    final State imaginaryDefendingState = state.put(opponent, defendingLocation);
                    if (!hasFork(imaginaryDefendingState, opponent))
                        forkBlockingLocations.add(attackLocation);
                }
            }

            return forkBlockingLocations;
        }

        private List<Location> getAttackLocations(State state, Player player, List<Location> locations) {
            final ArrayList<Location> attackLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(player, location);
                if (hasAttack(imaginaryState, player))
                    attackLocations.add(location);
            }

            return attackLocations;
        }

        private boolean hasAttack(State state, Player player) {
            final List<Location> imaginaryAvailableLocations = new StateAnalyser(state).getAvailableLocationsFrom(Location.getAll());
            final List<Location> possibleWinnings = getPossibleWinnings(state, player, imaginaryAvailableLocations);
            return !possibleWinnings.isEmpty();
        }

        private List<Location> getPossibleWinnings(State state, Player player, Iterable<Location> locations) {
            final ArrayList<Location> winningLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(player, location);
                final Logic logic = new Logic(imaginaryState);
                if (logic.hasWon(player))
                    winningLocations.add(location);
            }

            return winningLocations;
        }

        private List<Location> getPossibleForks(State state, Player player, Iterable<Location> locations){
            final ArrayList<Location> forkLocations = new ArrayList<>();

            for (Location location : locations){
                final State imaginaryState = state.put(player, location);
                if (hasFork(imaginaryState, player))
                    forkLocations.add(location);
            }

            return forkLocations;
        }

        private boolean hasFork(State state, Player player) {
            return getPossibleWinnings(state, player, new StateAnalyser(state).getAvailableLocationsFrom(Location.getAll())).size() > 1;
        }

        private <T> T getFirst(List<T> list) {
            return list.get(0);
        }

        private <T> List<T> removeFrom(List<T> all, List<T> remove) {
            final ArrayList<T> list = new ArrayList<>(all);
            list.removeAll(remove);
            return list;
        }
    }
}
