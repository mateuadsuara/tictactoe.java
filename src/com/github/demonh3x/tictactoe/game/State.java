package com.github.demonh3x.tictactoe.game;

import java.util.*;

public class State {
    public static State empty(Board board){
        return new State(board, Player.X, new HashMap<Location, Player>());
    }

    public final Board board;
    public final Player decisionMaker;
    private final Map<Location, Player> pieces;

    private State(Board board, Player decisionMaker, Map<Location, Player> pieces) {
        this.board = board;
        this.decisionMaker = decisionMaker;
        this.pieces = Collections.unmodifiableMap(pieces);
    }

    public Player lookAt(Location l){
        checkLocationValidity(l);
        return pieces.get(l);
    }

    public State play(Location location) {
        checkLocationValidity(location);
        final HashMap<Location, Player> newPieces = new HashMap<>(this.pieces);
        newPieces.put(location, decisionMaker);
        return new State(board, decisionMaker.next(), newPieces);
    }

    private void checkLocationValidity(Location l) {
        if (!board.contains(l))
            throw new IllegalArgumentException(String.format(
                    "The location %s is not a valid location inside the board!",
                    l.toString()
            ));
    }

    public boolean isEmptyAt(Location l) {
        return lookAt(l) == null;
    }

    public State skipTurn() {
        return new State(board, decisionMaker.next(), pieces);
    }
}
