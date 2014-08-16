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
        return new Play(representedPlayer, new LocationDecision(state).get());
    }

    private class LocationDecision {
        private State state;

        public LocationDecision(State state) {
            this.state = state;
        }

        public Location get() {
            return getFirst(getAvailable());
        }

        private Location getFirst(List<Location> locations) {
            return locations.get(0);
        }

        private List<Location> getAvailable() {
            final ArrayList<Location> available = new ArrayList<>();

            for (Location l : Location.getAll()){
                if (state.isEmptyAt(l))
                    available.add(l);
            }

            return available;
        }
    }
}
