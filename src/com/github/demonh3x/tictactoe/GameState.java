package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;
    private static final int REQUIRED_AMOUNT_OF_PIECES = ROWS * COLUMNS;

    private final List<Piece> pieces;

    public GameState(List<Piece> pieces) {
        if (pieces.size() != REQUIRED_AMOUNT_OF_PIECES)
            throw new IllegalArgumentException("A game state should have " + REQUIRED_AMOUNT_OF_PIECES + " pieces!");

        this.pieces = Collections.unmodifiableList(pieces);
    }

    public Piece lookAt(Location l){
        return pieces.get(getIndex(l));
    }

    private int getIndex(Location l) {
        return (l.y * ROWS) + l.x;
    }

    public GameState put(Piece p, Location l) {
        final ArrayList<Piece> newPieces = new ArrayList<>(this.pieces);
        newPieces.set(getIndex(l), p);
        return new GameState(newPieces);
    }
}
