package com.github.demonh3x.tictactoe.game;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclingIterator<E> implements Iterator<E> {
    private final List<E> elements;
    private int index;

    public CyclingIterator(List<E> elements) {
        this.elements = elements;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return !elements.isEmpty();
    }

    @Override
    public E next() {
        if (!hasNext())
            throw new NoSuchElementException();

        final E e = elements.get(index);
        index = getNextIndex();

        return e;
    }

    private int getNextIndex() {
        return (index +1) % elements.size();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
