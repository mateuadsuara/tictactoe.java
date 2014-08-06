package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameState {
    public static GameState empty(){
        return new GameState(Arrays.<Player>asList(null, null, null, null, null, null, null, null, null));
    }

    private final List<Player> pieces;

    private GameState(List<Player> pieces) {
        this.pieces = Collections.unmodifiableList(pieces);
    }

    public Player lookAt(Location l){
        return pieces.get(getIndex(l));
    }

    private int getIndex(Location l) {
        return (l.y * Location.ROWS) + l.x;
    }

    public GameState put(Player p, Location l) {
        final ArrayList<Player> newPieces = new ArrayList<>(this.pieces);
        newPieces.set(getIndex(l), p);
        return new GameState(newPieces);
    }

    public boolean isEmptyAt(Location l) {
        return lookAt(l) == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GameState))
            return false;

        GameState other = (GameState) obj;

        return this.pieces.equals(other.pieces);
    }
}
