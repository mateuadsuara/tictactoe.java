package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

public class StateLiteral {
    public static State create(Player... pieces) {
        final State gameState = State.empty();
        return gameState
                .put(pieces[0], new Location(0, 0))
                .put(pieces[1], new Location(1, 0))
                .put(pieces[2], new Location(2, 0))
                .put(pieces[3], new Location(0, 1))
                .put(pieces[4], new Location(1, 1))
                .put(pieces[5], new Location(2, 1))
                .put(pieces[6], new Location(0, 2))
                .put(pieces[7], new Location(1, 2))
                .put(pieces[8], new Location(2, 2));
    }
}
