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

    private static class LocationDecision {
        private final Player player;
        private final Player opponent;
        private final State state;

        public LocationDecision(Player player, Player opponent, State state) {
            this.player = player;
            this.opponent = opponent;
            this.state = state;
        }

        private static final List<Location> ALL = Location.getAll();
        private static final List<Location> CENTERS = Arrays.asList(new Location(1, 1));
        private static final List<Location> CORNERS = Arrays.asList(new Location(0, 0), new Location(2, 2), new Location(0, 2), new Location(2, 0));
        private static final List<Location> SIDES = Arrays.asList(new Location(1, 0), new Location(1, 2), new Location(0, 1), new Location(2, 1));

        public Location get() {
            final Results allLocations = new Results(state, ALL);
            final Results availableLocations = allLocations.available();

            final StateAnalyser analyser = new StateAnalyser(state);

            final Results winnableLocations = availableLocations.winnableBy(player);
            if (winnableLocations.exist())
                return winnableLocations.first();

            final Results losingLocations = availableLocations.winnableBy(opponent);
            if (losingLocations.exist())
                return losingLocations.first();

            final Results ownForkLocations = availableLocations.forkableBy(player);
            if (ownForkLocations.exist())
                return ownForkLocations.first();

            final Results opponentForkLocations = availableLocations.forkableBy(opponent);
            if (opponentForkLocations.exist())
                return availableLocations.forkBlockableBy(player, opponent).first();

            final Results availableCenters = new Results(state, CENTERS).available();
            if (availableCenters.exist())
                return availableCenters.first();

            final Results corners = new Results(state, CORNERS);
            final Results opponentCorners = corners.occupiedBy(opponent);
            final Results availableOppositeCorners = new Results(state, opposite(opponentCorners)).available();
            if (availableOppositeCorners.exist())
                return availableOppositeCorners.first();

            final Results availableCorners = corners.available();
            if (availableCorners.exist())
                return availableCorners.first();

            final Results availableSides = new Results(state, SIDES).available();
            if (availableSides.exist())
                return availableSides.first();

            throw new RuntimeException("Unhandled possibility!");
        }

        private List<Location> opposite(Iterable<Location> locations){
            final ArrayList<Location> oppositeLocations = new ArrayList<>();

            for (Location location : locations) {
                oppositeLocations.add(location.opposite());
            }

            return oppositeLocations;
        }

        private <T> T getFirst(List<T> list) {
            return list.get(0);
        }
    }
}
