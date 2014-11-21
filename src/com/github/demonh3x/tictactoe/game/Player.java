package com.github.demonh3x.tictactoe.game;

public final class Player {
    public static final Player X;
    public static final Player O;

    static {
        X = new Player();
        O = new Player();

        X.setNext(O);
        O.setNext(X);
    }

    private Player next;

    private Player(){}

    private void setNext(Player player) {
        this.next = player;
    }

    public Player next() {
        return next;
    }
}
