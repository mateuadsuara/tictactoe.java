package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Player;

public class WinningOutcome implements GameTree {
    private final Player winner;

    public WinningOutcome(Player winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WinningOutcome that = (WinningOutcome) o;

        if (!winner.equals(that.winner)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return winner.hashCode();
    }

    @Override
    public String toString() {
        return "WinningOutcome{" +
                 winner +
                '}';
    }

    @Override
    public Strategy bestStrategy() {
        return new Strategy(-1);
    }
}
