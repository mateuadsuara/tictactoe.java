package com.github.demonh3x.tictactoe.ai.minmax;

public final class DrawOutcome implements GameTree {
    private static final DrawOutcome INSTANCE = new DrawOutcome();

    public static DrawOutcome get() {
        return INSTANCE;
    }

    private DrawOutcome(){}

    @Override
    public String toString() {
        return "Draw";
    }

    @Override
    public Strategy bestStrategy() {
        return new Strategy(0);
    }
}
