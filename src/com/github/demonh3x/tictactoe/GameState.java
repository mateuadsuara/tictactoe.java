package com.github.demonh3x.tictactoe;

public class GameState {
    private final Piece[] pieces;

    public GameState(Piece... pieces) {
        this.pieces = pieces;
    }

    public Boolean isFinished() {
        return pieces[0] != null ||
                pieces[6] != null && pieces[7] != null && pieces[8] != null;
    }
}
