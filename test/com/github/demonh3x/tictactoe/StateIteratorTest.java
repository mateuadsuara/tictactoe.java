package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StateIteratorTest {
    public class InteractorSpy implements Interactor {
        public Location locationToPlay;
        public List<State> receivedStatesToPlay = new ArrayList<>();

        @Override
        public Location play(State state) {
            receivedStatesToPlay.add(state);
            return locationToPlay;
        }
    }

    private Iterator<Interactor> iterate(Interactor... interactors) {
        final LinkedList<Interactor> list = new LinkedList<>();
        Collections.addAll(list, interactors);
        return list.iterator();
    }

    InteractorSpy xInteractor;
    InteractorSpy oInteractor;

    @Before
    public void setUp() {
        xInteractor = new InteractorSpy();
        oInteractor = new InteractorSpy();
    }

    @Test
    public void GivenAnEmptyPlayerIterator_ShouldNotHaveNextState() {
        final StateIterator iterator = new StateIterator(StateLiteral.empty(), iterate());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void WhenRemoving_ShouldThrowUnsupportedOperation() {
        final StateIterator iterator = new StateIterator(StateLiteral.empty(), iterate());
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void GivenAnEmptyPlayerIterator_WhenGettingTheNextState_ShouldThrowNoSuchElement() {
        final StateIterator iterator = new StateIterator(StateLiteral.empty(), iterate());
        iterator.next();
    }

    @Test
    public void GivenAPlayerToIterate_ShouldHaveNextState() {
        final StateIterator iterator = new StateIterator(StateLiteral.empty(), iterate(xInteractor));
        assertTrue(iterator.hasNext());
    }

    @Test
    public void GivenAPlayerToIterate_WhenGettingTheNextState_ShouldAskThatPlayersInteractor() {
        final State initialState = StateLiteral.empty();
        final StateIterator iterator = new StateIterator(initialState, iterate(xInteractor));
        xInteractor.locationToPlay = new Location(0, 0);
        iterator.next();
        assertThat(xInteractor.receivedStatesToPlay.size(), is(1));
        assertThat(xInteractor.receivedStatesToPlay.get(0), is(sameInstance(initialState)));
    }

    @Test
    public void GivenAPlayerToIterate_BeforeGettingTheNextState_ShouldntHaveAskedThatPlayersInteractor() {
        new StateIterator(StateLiteral.empty(), iterate(xInteractor));
        assertThat(xInteractor.receivedStatesToPlay.size(), is(0));
    }

    @Test
    public void GivenOnePlayerToIterate_AfterGettingTheFirstState_ShouldNotHaveNextState() {
        final State initialState = StateLiteral.empty();
        final StateIterator iterator = new StateIterator(initialState, iterate(xInteractor));
        xInteractor.locationToPlay = new Location(0, 0);
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void GivenTwoPlayersToIterate_WhenGettingTheSecondState_ShouldAskTheSecondPlayersInteractorWithThePreviousState() {
        final State initialState = StateLiteral.empty();
        final StateIterator iterator = new StateIterator(initialState, iterate(xInteractor, oInteractor));
        xInteractor.locationToPlay = new Location(0, 0);
        final State previousState = iterator.next();
        oInteractor.locationToPlay = new Location(0, 1);
        iterator.next();
        assertThat(oInteractor.receivedStatesToPlay.size(), is(1));
        assertThat(oInteractor.receivedStatesToPlay.get(0), is(previousState));
        assertThat(oInteractor.receivedStatesToPlay.get(0), is(not(initialState)));
    }
}
