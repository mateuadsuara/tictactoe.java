package com.github.demonh3x.tictactoe.ai.minmax;

public final class WinningOutcome implements GameTree {
    private static final WinningOutcome INSTANCE = new WinningOutcome();

    public static WinningOutcome get() {
        return INSTANCE;
    }

    private WinningOutcome(){}

    @Override
    public String toString() {
        return "Win";
    }

    @Override
    public Strategy bestStrategy() {
        return new Strategy(-1);
    }
}
