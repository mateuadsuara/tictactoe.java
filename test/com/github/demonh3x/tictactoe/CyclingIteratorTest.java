package com.github.demonh3x.tictactoe;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CyclingIteratorTest {
    @Test(expected = UnsupportedOperationException.class)
    public void givenAListAnElement_whenRemoving_throwsUnsupportedOperation() {
        Iterator<Object> iterator = new CyclingIterator<>(Arrays.asList(new Object()));
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void givenAnEmptyList_whenAskingForTheNextElement_throwsNoSuchElement() {
        Iterator<Object> iterator = new CyclingIterator<>(Arrays.asList());
        iterator.next();
    }

    @Test
    public void givenAnEmptyList_doesNotHaveNext() {
        Iterator<Object> iterator = new CyclingIterator<>(Arrays.asList());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void givenAListWithAnElement_doesHaveNext() {
        Iterator<Object> iterator = new CyclingIterator<>(Arrays.asList(new Object()));
        assertTrue(iterator.hasNext());
    }

    @Test
    public void givenAListWithAnElement_allwaysHasThatElementNext() {
        final Object element = new Object();
        Iterator<Object> iterator = new CyclingIterator<>(Arrays.asList(element));

        for (int i = 0; i < 10; i++){
            assertEquals(element, iterator.next());
        }
    }

    @Test
    public void givenAListWithTwoElements_afterTheFirstElementComesTheSecondOne() {
        final Object first = new Object();
        final Object second = new Object();
        Iterator<Object> iterator = new CyclingIterator<>(Arrays.asList(first, second));

        iterator.next();
        assertEquals(second, iterator.next());
    }

    @Test
    public void givenAListWithTwoElements_afterTheSecondElementComesTheFirstOneAgain() {
        final Object first = new Object();
        final Object second = new Object();
        Iterator<Object> iterator = new CyclingIterator<>(Arrays.asList(first, second));

        iterator.next();
        iterator.next();

        assertEquals(first, iterator.next());
    }
}
