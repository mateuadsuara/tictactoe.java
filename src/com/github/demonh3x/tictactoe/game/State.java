package com.github.demonh3x.tictactoe.game;

import java.util.*;

public class State {
    public static State empty(Board board){
        return new State(board, new HashMap<Location, Player>());
    }

    public final Board board;
    private final Map<Location, Player> pieces;

    private State(Board board, Map<Location, Player> pieces) {
        this.board = board;
        this.pieces = Collections.unmodifiableMap(pieces);
    }

    public Player lookAt(Location l){
        if (!board.contains(l))
            throw new IllegalArgumentException(String.format(
                    "The location %s is not a valid location inside the board!",
                    l.toString()
            ));

        return pieces.get(l);
    }

    public State put(Player p, Location l) {
        final HashMap<Location, Player> newPieces = new HashMap<>(this.pieces);
        newPieces.put(l, p);
        return new State(board, newPieces);
    }

    public boolean isEmptyAt(Location l) {
        return lookAt(l) == null;
    }
}
