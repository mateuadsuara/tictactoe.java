package com.github.demonh3x.tictactoe;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class StateIterator implements Iterator<GameState> {
    private GameState state;
    private final Map<Player, GameInteractor> interactors;
    private final Iterator<Player> players;

    public StateIterator(GameState initial, Map<Player, GameInteractor> interactors, Iterator<Player> turns) {
        this.state = initial;
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

        final Player currentPlayer = players.next();
        final Location chosenLocation = interactors.get(currentPlayer).play(state);
        state = state.put(currentPlayer, chosenLocation);

        return state;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
