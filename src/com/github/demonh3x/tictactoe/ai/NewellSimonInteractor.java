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
            final StateAnalyser analyser = new StateAnalyser(state);

            final List<Location> availableLocations = analyser.getAvailableLocationsFrom(Location.getAll());

            final List<Location> winningLocations = analyser.getPossibleWinnings(player, availableLocations);
            if (!winningLocations.isEmpty())
                return getFirst(winningLocations);

            final List<Location> losingLocations = analyser.getPossibleWinnings(opponent, availableLocations);
            if (!losingLocations.isEmpty())
                return getFirst(losingLocations);

            final List<Location> forkLocations = analyser.getPossibleForks(player, availableLocations);
            if (!forkLocations.isEmpty())
                return getFirst(forkLocations);

            final List<Location> opponentForkLocations = analyser.getPossibleForks(opponent, availableLocations);
            if (!opponentForkLocations.isEmpty())
                return getFirst(analyser.getForkBlockingLocations(player, opponent, availableLocations));

            final Location center = new Location(1, 1);
            if (state.isEmptyAt(center))
                return center;

            final List<Location> corners = Arrays.asList(new Location(0, 0), new Location(2, 2), new Location(0, 2), new Location(2, 0));
            final List<Location> opponentCorners = analyser.getOccupiedBy(opponent, corners);
            final List<Location> availableOppositeCorners = analyser.getAvailableLocationsFrom(opposite(opponentCorners));
            if (!availableOppositeCorners.isEmpty())
                return getFirst(availableOppositeCorners);

            final List<Location> availableCorners = analyser.getAvailableLocationsFrom(corners);
            if (!availableCorners.isEmpty())
                return getFirst(availableCorners);

            final List<Location> sides = Arrays.asList(new Location(1, 0), new Location(1, 2), new Location(0, 1), new Location(2, 1));
            final List<Location> availableSides = analyser.getAvailableLocationsFrom(sides);
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

        private <T> T getFirst(List<T> list) {
            return list.get(0);
        }
    }
}
