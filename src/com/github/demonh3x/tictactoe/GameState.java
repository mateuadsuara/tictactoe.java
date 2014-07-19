package com.github.demonh3x.tictactoe;

public class GameState {
    private final Piece[] pieces;

    public GameState(Piece... pieces) {
        this.pieces = pieces;
    }

    public Boolean hasAWinner() {
        return false;
    }

    public Boolean isFinished() {
        return pieces[0] != null;
    }
}
