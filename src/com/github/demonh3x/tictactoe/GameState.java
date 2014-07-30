package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameState {
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
        return (l.y * Location.ROWS) + l.x;
    }

    public GameState put(Piece p, Location l) {
        final ArrayList<Piece> newPieces = new ArrayList<>(this.pieces);
        newPieces.set(getIndex(l), p);
        return new GameState(newPieces);
    }
}
