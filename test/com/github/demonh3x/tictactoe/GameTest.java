package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;

import static com.github.demonh3x.tictactoe.TestDoubleInteractor.*;
import static com.github.demonh3x.tictactoe.TestDoubleInteractor.PlaysRecorder.LocationWhereToPlay.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(HierarchicalContextRunner.class)
public class GameTest {
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
    public void BeforeRunningTheGame_NoOneShouldHaveBeenNotified() {
        assertThat(observer1.statesReceived.size(), is(0));
        assertThat(observer2.statesReceived.size(), is(0));
        assertThat(interactor1.statesReceived.size(), is(0));
        assertThat(interactor2.statesReceived.size(), is(0));
    }

    public class GivenADrawGame {
        @Before
        public void setUp() {
            final PlaysRecorder player1Recorder = new PlaysRecorder(interactor1);
            final PlaysRecorder player2Recorder = new PlaysRecorder(interactor2);

            final PlaysRecorder.LocationWhereToPlay X = HERE;
            final PlaysRecorder.LocationWhereToPlay O = HERE;
            final PlaysRecorder.LocationWhereToPlay _ = NOT_HERE;

            // X, O, X
            // O, X, X
            // O, X, O

            player1Recorder.recordPlayToDo(
                    X, _, _,
                    _, _, _,
                    _, _, _
            );

            player2Recorder.recordPlayToDo(
                    _, O, _,
                    _, _, _,
                    _, _, _
            );

            player1Recorder.recordPlayToDo(
                    _, _, X,
                    _, _, _,
                    _, _, _
            );

            player2Recorder.recordPlayToDo(
                    _, _, _,
                    O, _, _,
                    _, _, _
            );

            player1Recorder.recordPlayToDo(
                    _, _, _,
                    _, X, _,
                    _, _, _
            );

            player2Recorder.recordPlayToDo(
                    _, _, _,
                    _, _, _,
                    O, _, _
            );

            player1Recorder.recordPlayToDo(
                    _, _, _,
                    _, _, X,
                    _, _, _
            );

            player2Recorder.recordPlayToDo(
                    _, _, _,
                    _, _, _,
                    _, _, O
            );

            player1Recorder.recordPlayToDo(
                    _, _, _,
                    _, _, _,
                    _, X, _
            );

            game.run();
        }

        public class WhenRunningTheGame {
            @Before
            public void setUp() {
                game.run();
            }

            @Test
            public void ShouldNotifyTheObserversFirstWithTheInitialState() {
                GameState initialState = GameState.empty();

                assertObserversReceivedState(Arrays.asList(observer1, observer2), 0, initialState);
            }
        }
    }

    private void assertObserversReceivedState(List<TestDoubleObserver> observers, int index, GameState expectedState) {
        for (TestDoubleObserver observer : observers){
            assertObserverReceivedState(observer, index, expectedState);
        }
    }

    private void assertObserverReceivedState(TestDoubleObserver observer, int index, GameState expectedState) {
        assertThat(observer.statesReceived.size(), greaterThan(index));
        assertThat(observer.statesReceived.get(index), is(expectedState));
    }
}
