package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.game.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateAnalyser {
    private final State state;

    public StateAnalyser(State state){
        this.state = state;
    }

    public List<Location> getAvailableLocationsFrom(Iterable<Location> toLookAt) {
        final ArrayList<Location> available = new ArrayList<>();

        for (Location location : toLookAt){
            if (state.isEmptyAt(location))
                available.add(location);
        }

        return available;
    }

    public List<Location> getPossibleWinnings(Player player, Iterable<Location> locations) {
        final ArrayList<Location> winningLocations = new ArrayList<>();

        for (Location location : locations){
            final State imaginaryState = state.put(new Play(player, location));
            final Logic logic = new Logic(imaginaryState);
            if (logic.hasWon(player))
                winningLocations.add(location);
        }

        return winningLocations;
    }

    public List<Location> getPossibleForks(Player player, Iterable<Location> locations){
        final ArrayList<Location> forkLocations = new ArrayList<>();

        for (Location location : locations){
            final State imaginaryState = state.put(new Play(player, location));
            if (hasFork(imaginaryState, player))
                forkLocations.add(location);
        }

        return forkLocations;
    }

    private boolean hasFork(State state, Player player) {
        final StateAnalyser analyser = new StateAnalyser(state);
        return analyser.getPossibleWinnings(player, analyser.getAvailableLocationsFrom(getAllLocations())).size() > 1;
    }

    public List<Location> getForkBlockingLocations(Player player, Player opponent, Iterable<Location> locations) {
        final ArrayList<Location> forkBlockingLocations = new ArrayList<>();

        final List<Location> attackLocations = getAttackLocations(state, player, locations);

        for (Location attackLocation : attackLocations){
            final State imaginaryState = state.put(new Play(player, attackLocation));
            final List<Location> imaginaryAvailableLocations = removeFrom(attackLocations, Arrays.asList(attackLocation));
            final List<Location> imaginaryWinnings = new StateAnalyser(imaginaryState).getPossibleWinnings(player, imaginaryAvailableLocations);
            for (Location defendingLocation : imaginaryWinnings) {
                final State imaginaryDefendingState = state.put(new Play(opponent, defendingLocation));
                if (!hasFork(imaginaryDefendingState, opponent))
                    forkBlockingLocations.add(attackLocation);
            }
        }

        return forkBlockingLocations;
    }

    private List<Location> getAttackLocations(State state, Player player, Iterable<Location> locations) {
        final ArrayList<Location> attackLocations = new ArrayList<>();

        for (Location location : locations){
            final State imaginaryState = state.put(new Play(player, location));
            if (hasAttack(imaginaryState, player))
                attackLocations.add(location);
        }

        return attackLocations;
    }

    private boolean hasAttack(State state, Player player) {
        final StateAnalyser analyser = new StateAnalyser(state);
        final List<Location> imaginaryAvailableLocations = analyser.getAvailableLocationsFrom(getAllLocations());
        final List<Location> possibleWinnings = analyser.getPossibleWinnings(player, imaginaryAvailableLocations);
        return !possibleWinnings.isEmpty();
    }

    private List<Location> getAllLocations() {
        return state.board.getAllLocations();
    }

    private <T> List<T> removeFrom(List<T> all, List<T> remove) {
        final ArrayList<T> list = new ArrayList<>(all);
        list.removeAll(remove);
        return list;
    }

    public List<Location> getOccupiedBy(Player player, Iterable<Location> locations) {
        final ArrayList<Location> occupiedByPlayer = new ArrayList<>();

        for (Location location : locations) {
            if (state.lookAt(location) == player)
                occupiedByPlayer.add(location);
        }

        return occupiedByPlayer;
    }
}
