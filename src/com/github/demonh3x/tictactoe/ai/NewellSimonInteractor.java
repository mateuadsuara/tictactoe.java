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

            final List<Location> forkLocations = analyser.getPossibleForks(player, availableLocations);
            if (!forkLocations.isEmpty())
                return getFirst(forkLocations);

            final List<Location> opponentForkLocations = analyser.getPossibleForks(opponent, availableLocations);
            if (!opponentForkLocations.isEmpty())
                return getFirst(analyser.getForkBlockingLocations(player, opponent, availableLocations));

            final List<Location> availableCenters = analyser.getAvailableLocationsFrom(CENTERS);
            if (!availableCenters.isEmpty())
                return getFirst(availableCenters);

            final List<Location> opponentCorners = analyser.getOccupiedBy(opponent, CORNERS);
            final List<Location> availableOppositeCorners = analyser.getAvailableLocationsFrom(opposite(opponentCorners));
            if (!availableOppositeCorners.isEmpty())
                return getFirst(availableOppositeCorners);

            final List<Location> availableCorners = analyser.getAvailableLocationsFrom(CORNERS);
            if (!availableCorners.isEmpty())
                return getFirst(availableCorners);

            final List<Location> availableSides = analyser.getAvailableLocationsFrom(SIDES);
            if (!availableSides.isEmpty())
                return getFirst(availableSides);

            throw new RuntimeException("Unhandled possibility!");
        }

        private List<Location> opposite(List<Location> locations){
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
