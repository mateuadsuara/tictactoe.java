package com.github.demonh3x.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameIteratorTest {
    public class GameInteractorSpy implements GameInteractor {
        public Location locationToPlay;
        public List<GameState> receivedStatesToPlay = new ArrayList<>();

        @Override
        public Location play(GameState state) {
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

    GameInteractorSpy xInteractor;
    GameInteractorSpy oInteractor;

    Map<Player, GameInteractor> interactors;

    @Before
    public void setUp() {
        xPlayer = new Player();
        oPlayer = new Player();

        xInteractor = new GameInteractorSpy();
        oInteractor = new GameInteractorSpy();

        interactors = new HashMap<>();
        interactors.put(xPlayer, xInteractor);
        interactors.put(oPlayer, oInteractor);
    }

    @Test
    public void GivenAnEmptyPlayerIterator_ShouldNotHaveNextState() {
        final GameIterator game = new GameIterator(GameState.empty(), interactors, iterate());
        assertFalse(game.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void WhenRemoving_ShouldThrowUnsupportedOperation() {
        final GameIterator game = new GameIterator(GameState.empty(), interactors, iterate());
        game.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void GivenAnEmptyPlayerIterator_WhenGettingTheNextState_ShouldThrowNoSuchElement() {
        final GameIterator game = new GameIterator(GameState.empty(), interactors, iterate());
        game.next();
    }

    @Test
    public void GivenAPlayerToIterate_ShouldHaveNextState() {
        final GameIterator game = new GameIterator(GameState.empty(), interactors, iterate(xPlayer));
        assertTrue(game.hasNext());
    }

    @Test
    public void GivenAPlayerToIterate_WhenGettingTheNextState_ShouldAskTheInteractorOfThatPlayer() {
        final GameIterator game = new GameIterator(GameState.empty(), interactors, iterate(xPlayer));
        game.next();
        assertThat(xInteractor.receivedStatesToPlay.size(), is(1));
    }
}