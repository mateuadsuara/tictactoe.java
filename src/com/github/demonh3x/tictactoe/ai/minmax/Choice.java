package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;

import java.util.*;

public class Choice implements GameTree {
    private final Player decisionMaker;
    private final Map<Location, GameTree> subTrees;

    public Choice(Player decisionMaker, Map<Location, GameTree> subTrees) {
        if (subTrees.isEmpty())
            throw new IllegalArgumentException("The SubTrees shouldn't be empty!");
        this.decisionMaker = decisionMaker;
        this.subTrees = subTrees;
    }

    public Choice(Player decisionMaker, final Location location, final GameTree next) {
        this(decisionMaker, new HashMap<Location, GameTree>(){{
            put(location, next);
        }});
    }

    @Override
    public String toString() {
        return "Choice{" +
                "decisionMaker=" + decisionMaker +
                ", subTrees=" + subTrees +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Choice choice = (Choice) o;

        if (!decisionMaker.equals(choice.decisionMaker)) return false;
        if (!subTrees.equals(choice.subTrees)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = decisionMaker.hashCode();
        result = 31 * result + subTrees.hashCode();
        return result;
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
