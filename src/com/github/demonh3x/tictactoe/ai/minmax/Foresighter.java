package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.Arrays;
import java.util.Iterator;

public class Foresighter implements Iterable<State>{
    private final Player current;
    private final State base;

    public Foresighter(Player current, State base) {
        this.current = current;
        this.base = base;
    }

    @Override
    public Iterator<State> iterator() {
        for (Location l : base.board.getAllLocations())
            if (base.isEmptyAt(l))
                return Arrays.asList(
                        base.put(current, l)
                ).iterator();

        return Arrays.<State>asList().iterator();
    }
}
