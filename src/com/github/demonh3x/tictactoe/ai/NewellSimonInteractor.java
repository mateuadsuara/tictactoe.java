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

    private static interface MoveOption {
        public boolean isAvailable();
        public Location getLocation();
    }

    private static class WinOption implements MoveOption {
        private final Results winnableLocations;

        private WinOption(State state, Player player) {
            winnableLocations = new Results(state, Location.getAll()).available().winnableBy(player);
        }

        @Override
        public boolean isAvailable() {
            return winnableLocations.exist();
        }

        @Override
        public Location getLocation() {
            return winnableLocations.first();
        }
    }

    private static class BlockOption implements MoveOption {
        private final Results losingLocations;

        private BlockOption(State state, Player opponent) {
             losingLocations = new Results(state, Location.getAll()).available().winnableBy(opponent);
        }

        @Override
        public boolean isAvailable() {
            return losingLocations.exist();
        }

        @Override
        public Location getLocation() {
            return losingLocations.first();
        }
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
        private static final List<Location> CENTERS = Location.getCenters();
        private static final List<Location> CORNERS = Location.getCorners();
        private static final List<Location> SIDES = Location.getSides();

        public Location get() {
            final Results allLocations = new Results(state, ALL);
            final Results availableLocations = allLocations.available();

            final MoveOption win = new WinOption(state, player);
            if (win.isAvailable())
                return win.getLocation();

            final MoveOption block = new BlockOption(state, opponent);
            if (block.isAvailable())
                return block.getLocation();

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
    }
}
