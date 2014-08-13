package com.github.demonh3x.tictactoe;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class GameIterator implements Iterator<GameState> {
    public GameIterator(GameState initial, Map<Player, GameInteractor> interactors, Iterator<Player> turns) {
    }

    public boolean hasNext() {
        return false;
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
