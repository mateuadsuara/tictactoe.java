package com.github.demonh3x.tictactoe;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class GameIterator implements Iterator<GameState> {
    private final Iterator<Player> turns;

    public GameIterator(GameState initial, Map<Player, GameInteractor> interactors, Iterator<Player> turns) {
        this.turns = turns;
    }

    public boolean hasNext() {
        return turns.hasNext();
    }

    @Override
    public GameState next() {
        throw new NoSuchElementException();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
