package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.ai.interactors.FirstPossiblePlayInteractor;
import com.github.demonh3x.tictactoe.game.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class FirstPossiblePlayInteractorTest {
    Interactor interactor;

    @Before
    public void setUp() {
        interactor = new FirstPossiblePlayInteractor(new Player());
    }

    @Test
    public void GivenAnEmptyState_shouldReturnTheFirstLocation() {
        final State state = State.empty();

        final Location played = interactor.play(state).location;

        assertThat(played, is(new Location(0, 0)));
    }

    @Test
    public void GivenStateWithFirstPieceInPlace_shouldReturnTheSecondLocation() {
        final State state = State.empty()
                .put(new Player(), new Location(0, 0));

        final Location played = interactor.play(state).location;

        assertThat(played, is(new Location(1, 0)));
    }

    @Test
    public void GivenStateWithFirstTwoPiecesInPlace_shouldReturnTheThirdLocation() {
        final State state = State.empty()
                .put(new Player(), new Location(0, 0))
                .put(new Player(), new Location(1, 0));

        final Location played = interactor.play(state).location;

        assertThat(played, is(new Location(2, 0)));
    }

    @Test
    public void GivenStateWithFirstThreePiecesInPlace_shouldReturnTheFourthLocation() {
        final State state = State.empty()
                .put(new Player(), new Location(0, 0))
                .put(new Player(), new Location(1, 0))
                .put(new Player(), new Location(2, 0));

        final Location played = interactor.play(state).location;

        assertThat(played, is(new Location(0, 1)));
    }

    @Test (expected = RuntimeException.class)
    public void GivenStateWithAllPiecesInPlace_shouldThrowException() {
        final State state = State.empty()
                .put(new Player(), new Location(0, 0))
                .put(new Player(), new Location(1, 0))
                .put(new Player(), new Location(2, 0))
                .put(new Player(), new Location(0, 1))
                .put(new Player(), new Location(1, 1))
                .put(new Player(), new Location(2, 1))
                .put(new Player(), new Location(0, 2))
                .put(new Player(), new Location(1, 2))
                .put(new Player(), new Location(2, 2));

        interactor.play(state);
    }
}
