package com.github.demonh3x.tictactoe;

import java.util.List;

public class GameState {
    private final List<Piece> pieces;

    public GameState(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Boolean isFinished() {
        return isFull() || hasALine();
    }

    private boolean isFull() {
        for (Piece p : pieces){
            if (p == null) return false;
        }

        return true;
    }

    private boolean hasALine() {
        return pieces.get(6) == pieces.get(7) &&
                pieces.get(7) == pieces.get(8) &&
                pieces.get(8) != null;
    }
}
