package com.github.demonh3x.tictactoe.game;

public final class Player {
    public static final Player X;
    public static final Player O;

    static {
        X = new Player("X");
        O = new Player("O");

        X.setNext(O);
        O.setNext(X);
    }

    private final String symbol;

    private Player next;

    private Player(String symbol) {
        this.symbol = symbol;
    }

    private void setNext(Player player) {
        this.next = player;
    }

    public Player next() {
        return next;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
