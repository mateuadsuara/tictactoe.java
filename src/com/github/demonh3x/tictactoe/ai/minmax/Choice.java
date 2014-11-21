package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;

public class Choice implements GameTree {
    private final Player decisionMaker;
    private final Location location;
    private final GameTree next;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Choice choice = (Choice) o;

        if (!decisionMaker.equals(choice.decisionMaker)) return false;
        if (!location.equals(choice.location)) return false;
        if (!next.equals(choice.next)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = decisionMaker.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + next.hashCode();
        return result;
    }

    public Choice(Player decisionMaker, Location location, GameTree next) {
        this.decisionMaker = decisionMaker;
        this.location = location;
        this.next = next;
    }
}
