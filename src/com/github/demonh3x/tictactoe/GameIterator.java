package com.github.demonh3x.tictactoe;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class GameIterator implements Iterator<GameState> {
    private final Map<Player, GameInteractor> interactors;
    private final Iterator<Player> players;

    public GameIterator(GameState initial, Map<Player, GameInteractor> interactors, Iterator<Player> turns) {
        this.interactors = interactors;
        this.players = turns;
    }

    public boolean hasNext() {
        return players.hasNext();
    }

    @Override
    public GameState next() {
        if (!hasNext())
            throw new NoSuchElementException();

        interactors.get(players.next()).play(null);

        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
