package com.github.demonh3x.tictactoe;

public class Piece {
    private final Player owner;

    public Piece(Player owner) {
        this.owner = owner;
    }

    public boolean isOwnedBy(Player possibleOwner){
        return owner == possibleOwner;
    }
}
