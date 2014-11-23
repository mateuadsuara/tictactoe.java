package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;

import java.util.*;

public class Choice implements GameTree {
    private final Map<Location, GameTree> subTrees;

    public Choice(Map<Location, GameTree> subTrees) {
        if (subTrees.isEmpty())
            throw new IllegalArgumentException("The SubTrees shouldn't be empty!");
        this.subTrees = subTrees;
    }

    public Choice(final Location location, final GameTree next) {
        this(new HashMap<Location, GameTree>(){{
            put(location, next);
        }});
    }

    @Override
    public String toString() {
        return "Choice{" +
                subTrees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Choice choice = (Choice) o;

        if (!subTrees.equals(choice.subTrees)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return subTrees.hashCode();
    }

    @Override
    public Strategy bestStrategy() {
        Map<Integer, List<Location>> resultsByScore = new HashMap<>();
        resultsByScore.put(-1, new ArrayList<Location>());
        resultsByScore.put(0, new ArrayList<Location>());
        resultsByScore.put(1, new ArrayList<Location>());

        int bestScore = Integer.MIN_VALUE;

        for (Map.Entry<Location, GameTree> option : subTrees.entrySet()) {
            Location location = option.getKey();
            GameTree subTree = option.getValue();

            Strategy opponentStrategy = subTree.bestStrategy();
            int score = -opponentStrategy.score;
            resultsByScore.get(score).add(location);
            if (score > bestScore) {
                bestScore = score;
            }
        }

        return new Strategy(bestScore, resultsByScore.get(bestScore));
    }

}
