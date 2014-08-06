package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(HierarchicalContextRunner.class)
public class GameTest {
    private static class TestDoubleInteractor implements GameInteractor {
        public List<Location> playsToSend = new ArrayList<>();
        private int playIndex = 0;

        public List<GameState> statesReceived = new ArrayList<>();

        @Override
        public Location play(GameState state) {
            statesReceived.add(state);
            return this.playsToSend.get(playIndex++);
        }
    }

    private static class TestDoubleObserver implements GameObserver {
        public List<GameState> statesReceived = new ArrayList<>();

        @Override
        public void update(GameState state) {
            statesReceived.add(state);
        }
    }

    final Player player1, player2;
    final Map<Player, GameInteractor> interactors;
    final TestDoubleInteractor interactor1, interactor2;
    final List<GameObserver> observers;
    final TestDoubleObserver observer1, observer2;
    final Game game;

    public GameTest(){
        interactors = new HashMap<>();
        player1 = new Player();
        player2 = new Player();
        interactor1 = new TestDoubleInteractor();
        interactor2 = new TestDoubleInteractor();
        interactors.put(player1, interactor1);
        interactors.put(player2, interactor2);

        observers = new ArrayList<>();
        observer1 = new TestDoubleObserver();
        observer2 = new TestDoubleObserver();
        observers.add(observer1);
        observers.add(observer2);

        game = new Game(interactors, observers);
    }

    @Test
    public void BeforeStartingTheGame_NoOneShouldHaveBeenNotified() {
        assertThat(observer1.statesReceived.size(), is(0));
        assertThat(observer2.statesReceived.size(), is(0));
        assertThat(interactor1.statesReceived.size(), is(0));
        assertThat(interactor2.statesReceived.size(), is(0));
    }

    public class WhenStartingTheGame {
        @Before
        public void setUp() {
            game.start();
        }

        @Test
        public void ShouldNotifyTheObserversWithAnEmptyState() {
            GameState emptyState = GameState.empty();

            assertThat(observer1.statesReceived.size(), greaterThan(0));
            assertThat(observer2.statesReceived.size(), greaterThan(0));
            assertThat(observer1.statesReceived.get(0), is(emptyState));
            assertThat(observer2.statesReceived.get(0), is(emptyState));
        }
    }
}
