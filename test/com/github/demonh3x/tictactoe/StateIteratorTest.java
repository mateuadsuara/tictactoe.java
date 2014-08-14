package com.github.demonh3x.tictactoe;

import com.github.demonh3x.tictactoe.game.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
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

    private Iterator<Player> iterate(Player... players) {
        final LinkedList<Player> list = new LinkedList<>();
        Collections.addAll(list, players);
        return list.iterator();
    }

    Player xPlayer = new Player();
    Player oPlayer = new Player();

    InteractorSpy xInteractor;
    InteractorSpy oInteractor;

    Map<Player, Interactor> interactors;

    @Before
    public void setUp() {
        xPlayer = new Player();
        oPlayer = new Player();

        xInteractor = new InteractorSpy();
        oInteractor = new InteractorSpy();

        interactors = new HashMap<>();
        interactors.put(xPlayer, xInteractor);
        interactors.put(oPlayer, oInteractor);
    }

    @Test
    public void GivenAnEmptyPlayerIterator_ShouldNotHaveNextState() {
        final StateIterator iterator = new StateIterator(State.empty(), interactors, iterate());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void WhenRemoving_ShouldThrowUnsupportedOperation() {
        final StateIterator iterator = new StateIterator(State.empty(), interactors, iterate());
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void GivenAnEmptyPlayerIterator_WhenGettingTheNextState_ShouldThrowNoSuchElement() {
        final StateIterator iterator = new StateIterator(State.empty(), interactors, iterate());
        iterator.next();
    }

    @Test
    public void GivenAPlayerToIterate_ShouldHaveNextState() {
        final StateIterator iterator = new StateIterator(State.empty(), interactors, iterate(xPlayer));
        assertTrue(iterator.hasNext());
    }

    @Test
    public void GivenAPlayerToIterate_WhenGettingTheNextState_ShouldAskThatPlayersInteractor() {
        final State initialState = State.empty();
        final StateIterator iterator = new StateIterator(initialState, interactors, iterate(xPlayer));
        xInteractor.locationToPlay = new Location(0, 0);
        iterator.next();
        assertThat(xInteractor.receivedStatesToPlay.size(), is(1));
        assertThat(xInteractor.receivedStatesToPlay.get(0), is(sameInstance(initialState)));
    }

    @Test
    public void GivenAPlayerToIterate_BeforeGettingTheNextState_ShouldntHaveAskedThatPlayersInteractor() {
        final StateIterator iterator = new StateIterator(State.empty(), interactors, iterate(xPlayer));
        assertThat(xInteractor.receivedStatesToPlay.size(), is(0));
    }

    @Test
    public void GivenOnePlayerToIterate_AfterGettingTheFirstState_ShouldNotHaveNextState() {
        final State initialState = State.empty();
        final StateIterator iterator = new StateIterator(initialState, interactors, iterate(xPlayer));
        xInteractor.locationToPlay = new Location(0, 0);
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void GivenTwoPlayersToIterate_WhenGettingTheSecondState_ShouldAskTheSecondPlayersInteractorWithThePreviousState() {
        final State initialState = State.empty();
        final StateIterator iterator = new StateIterator(initialState, interactors, iterate(xPlayer, oPlayer));
        xInteractor.locationToPlay = new Location(0, 0);
        final State previousState = iterator.next();
        oInteractor.locationToPlay = new Location(0, 1);
        iterator.next();
        assertThat(oInteractor.receivedStatesToPlay.size(), is(1));
        assertThat(oInteractor.receivedStatesToPlay.get(0), is(previousState));
        assertThat(oInteractor.receivedStatesToPlay.get(0), is(not(initialState)));
    }
}
