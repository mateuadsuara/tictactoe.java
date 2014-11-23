package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;

import java.util.HashMap;
import java.util.Map;

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
}
