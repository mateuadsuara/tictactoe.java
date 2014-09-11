package com.github.demonh3x.tictactoe.game;

import java.util.*;

public class State {
    public static State empty(){
        return new State(new HashMap<Location, Player>());
    }

    private final Map<Location, Player> pieces;

    private State(Map<Location, Player> pieces) {
        this.pieces = Collections.unmodifiableMap(pieces);
    }

    public Player lookAt(Location l){
        return pieces.get(l);
    }

    public State put(Player p, Location l) {
        final HashMap<Location, Player> newPieces = new HashMap<>(this.pieces);
        newPieces.put(l, p);
        return new State(newPieces);
    }

    public boolean isEmptyAt(Location l) {
        return lookAt(l) == null;
    }
}
