package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Play;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.ArrayList;
import java.util.Iterator;

public class Foresighter implements Iterable<Play>{
    private final Player current;
    private final State base;

    public Foresighter(Player current, State base) {
        this.current = current;
        this.base = base;
    }

    @Override
    public Iterator<Play> iterator() {
        ArrayList<Play> futures = new ArrayList<>();

        for (Location l : base.board.getAllLocations())
            if (base.isEmptyAt(l))
                futures.add(new Play(current, l));

        return futures.iterator();
    }
}
