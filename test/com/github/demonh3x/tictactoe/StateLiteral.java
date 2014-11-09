package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.*;

public class StateLiteral {
    private static final TicTacToeBoard TIC_TAC_TOE_BOARD = new TicTacToeBoard();

    public static State empty(){
        return State.empty(TIC_TAC_TOE_BOARD);
    }

    public static State create(Player... pieces) {
        final State gameState = empty();
        return gameState
                .put(new Play(pieces[0], new Location(0, 0)))
                .put(new Play(pieces[1], new Location(1, 0)))
                .put(new Play(pieces[2], new Location(2, 0)))
                .put(new Play(pieces[3], new Location(0, 1)))
                .put(new Play(pieces[4], new Location(1, 1)))
                .put(new Play(pieces[5], new Location(2, 1)))
                .put(new Play(pieces[6], new Location(0, 2)))
                .put(new Play(pieces[7], new Location(1, 2)))
                .put(new Play(pieces[8], new Location(2, 2)));
    }
}
