package com.github.demonh3x.tictactoe.ai.options;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.Iterator;
import java.util.List;

public class Results implements Iterable<Location>{
    private final State state;
    private final StateAnalyser analyser;
    private final List<Location> locations;

    public Results(State state, List<Location> locations) {
        this.state = state;
        this.analyser = new StateAnalyser(state);
        this.locations = locations;
    }

    @Override
    public Iterator<Location> iterator() {
        return locations.iterator();
    }

    public boolean exist(){
        return !locations.isEmpty();
    }

    public Location first() {
        return locations.get(0);
    }

    public Results available() {
        final List<Location> availableLocations = analyser.getAvailableLocationsFrom(locations);
        return new Results(state, availableLocations);
    }

    public Results winnableBy(Player player) {
        final List<Location> possibleWinnings = analyser.getPossibleWinnings(player, locations);
        return new Results(state, possibleWinnings);
    }

    public Results forkableBy(Player player) {
        final List<Location> possibleForks = analyser.getPossibleForks(player, locations);
        return new Results(state, possibleForks);
    }

    public Results forkBlockableBy(Player player, Player opponent) {
        final List<Location> possibleBlocks = analyser.getForkBlockingLocations(player, opponent, locations);
        return new Results(state, possibleBlocks);
    }

    public Results occupiedBy(Player player) {
        final List<Location> occupiedBy = analyser.getOccupiedBy(player, locations);
        return new Results(state, occupiedBy);
    }
}
