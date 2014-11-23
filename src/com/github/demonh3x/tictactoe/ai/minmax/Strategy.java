package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;

import java.util.Arrays;
import java.util.List;

public class Strategy {
    public final int score;
    public final List<Location> locations;

    public Strategy(int score, List<Location> locations) {
        this.score = score;
        this.locations = locations;
    }

    public Strategy(int score, Location... locations) {
        this(score, Arrays.asList(locations));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Strategy strategy = (Strategy) o;

        if (score != strategy.score) return false;
        if (!locations.equals(strategy.locations)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = score;
        result = 31 * result + locations.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Strategy{" +
                "score=" + score +
                ", locations=" + locations +
                '}';
    }
}
