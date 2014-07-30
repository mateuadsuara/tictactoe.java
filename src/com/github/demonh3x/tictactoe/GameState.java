package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameState {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    public static GameState empty(){
        return new GameState(Arrays.<Piece>asList(null, null, null, null, null, null, null, null, null));
    }

    private final List<Piece> pieces;

    private GameState(List<Piece> pieces) {
        this.pieces = Collections.unmodifiableList(pieces);
    }

    public Piece lookAt(Location l){
        return pieces.get(getIndex(l));
    }

    private int getIndex(Location l) {
        if (l.x >= COLUMNS || l.x < 0 ||
            l.y >= ROWS || l.y < 0)
            invalidLocation(l);

        return (l.y * ROWS) + l.x;
    }

    private void invalidLocation(Location l) {
        throw new IllegalArgumentException("The location " + l.toString() + " is out of range!");
    }

    public GameState put(Piece p, Location l) {
        final ArrayList<Piece> newPieces = new ArrayList<>(this.pieces);
        newPieces.set(getIndex(l), p);
        return new GameState(newPieces);
    }
}
