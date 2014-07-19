package com.github.demonh3x.tictactoe;

public class GameState {
    private final Piece[] pieces;

    public GameState(Piece... pieces) {
        this.pieces = pieces;
    }

    public Boolean isFinished() {
        return isFull() || hasALine();
    }

    private boolean isFull() {
        return pieces[0] != null;
    }

    private boolean hasALine() {
        return pieces[6] != null && pieces[7] != null && pieces[8] != null;
    }
}
