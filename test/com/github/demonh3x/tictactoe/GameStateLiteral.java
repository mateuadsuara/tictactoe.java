package com.github.demonh3x.tictactoe;

public class GameStateLiteral {
    public static GameState create(Player... pieces) {
        final GameState gameState = GameState.empty();
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
