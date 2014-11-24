package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateLiteral {
    private static final Board BOARD = new SquareBoard(3);
    private static final Location[] LOCATIONS = new Location[]{
            new Location(0, 0),
            new Location(1, 0),
            new Location(2, 0),
            new Location(0, 1),
            new Location(1, 1),
            new Location(2, 1),
            new Location(0, 2),
            new Location(1, 2),
            new Location(2, 2)
    };

    public static State empty(){
        return State.empty(BOARD);
    }

    public static State create(Player... pieces) {
        List<Location> xLocations = new ArrayList<>();
        List<Location> oLocations = new ArrayList<>();
        for (int i = 0; i < LOCATIONS.length; i++) {
            if (pieces[i] == Player.X) xLocations.add(LOCATIONS[i]);
            else if (pieces[i] == Player.O) oLocations.add(LOCATIONS[i]);
        }
        if (oLocations.size() > xLocations.size())
            throw new IllegalArgumentException("X should have more or the same pieces placed");

        State gameState = empty();
        Iterator<Location> xIterator = xLocations.iterator();
        Iterator<Location> oIterator = oLocations.iterator();
        while (xIterator.hasNext()) {
            gameState = gameState.play(xIterator.next());
            if (oIterator.hasNext()) {
                gameState = gameState.play(oIterator.next());
            }
        }

        return gameState;
    }
}
