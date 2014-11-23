package com.github.demonh3x.tictactoe.game;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StateIterator implements Iterator<State> {
    private State state;
    private final Iterator<Interactor> interactors;

    public StateIterator(State initial, Iterator<Interactor> turns) {
        this.state = initial;
        this.interactors = turns;
    }

    public boolean hasNext() {
        return interactors.hasNext();
    }

    @Override
    public State next() {
        if (!hasNext())
            throw new NoSuchElementException();

        final Interactor interactor = interactors.next();
        state = state.play(interactor.play(state));

        return state;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
