package com.github.demonh3x.tictactoe.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class State {
    public static State empty(){
        return new State(Arrays.<Player>asList(null, null, null, null, null, null, null, null, null));
    }

    private final List<Player> pieces;

    private State(List<Player> pieces) {
        this.pieces = Collections.unmodifiableList(pieces);
    }

    public Player lookAt(Location l){
        return pieces.get(getIndex(l));
    }

    private int getIndex(Location l) {
        return (l.y * Location.ROWS) + l.x;
    }

    public State put(Player p, Location l) {
        final ArrayList<Player> newPieces = new ArrayList<>(this.pieces);
        newPieces.set(getIndex(l), p);
        return new State(newPieces);
    }

    public boolean isEmptyAt(Location l) {
        return lookAt(l) == null;
    }
}
