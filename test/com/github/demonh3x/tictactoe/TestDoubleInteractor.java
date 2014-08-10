package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.List;

public class TestDoubleInteractor implements GameInteractor {
    public static class PlaysRecorder {
        public static enum LocationWhereToPlay {
            HERE, NOT_HERE
        }

        private final TestDoubleInteractor interactor;

        public PlaysRecorder(TestDoubleInteractor interactor){
            this.interactor = interactor;
        }

        public void recordPlayToDo(Location location) {
            interactor.playsToSend.add(location);
        }

        public void recordPlayToDo(LocationWhereToPlay... locationsWhereToPlay) {
            if (locationsWhereToPlay.length != 9)
                throw new IllegalArgumentException("There are 9 locations available!");

            int locationsSelectedCount = 0;
            int locationToPlay = -1;
            for (int location = 0; location < locationsWhereToPlay.length; location++){
                LocationWhereToPlay hasToPlayThisLocation = locationsWhereToPlay[location];

                if (hasToPlayThisLocation == LocationWhereToPlay.HERE){
                    locationsSelectedCount++;
                    locationToPlay = location;
                }
            }

            if (locationsSelectedCount != 1)
                throw new IllegalArgumentException("There should be only one location to record where the player plays!");

            interactor.playsToSend.add(new Location(locationToPlay % 3, locationToPlay / 3));
        }
    }

    private List<Location> playsToSend = new ArrayList<>();
    private int playIndex = 0;

    public List<GameState> statesReceived = new ArrayList<>();

    @Override
    public Location play(GameState state) {
        statesReceived.add(state);
        return this.playsToSend.get(playIndex++);
    }
}