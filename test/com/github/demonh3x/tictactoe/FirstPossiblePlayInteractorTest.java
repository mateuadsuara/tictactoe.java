package com.github.demonh3x.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class FirstPossiblePlayInteractorTest {
    GameInteractor interactor;

    @Before
    public void setUp() {
        interactor = new FirstPossiblePlayInteractor();
    }

    @Test
    public void GivenAnEmptyState_shouldReturnTheFirstLocation() {
        final GameState gs = GameState.empty();

        final Location played = interactor.play(gs);

        assertThat(played, is(new Location(0, 0)));
    }

    @Test
    public void GivenStateWithFirstPieceInPlace_shouldReturnTheSecondLocation() {
        final GameState gs = GameState.empty()
                .put(new Player(), new Location(0, 0));

        final Location played = interactor.play(gs);

        assertThat(played, is(new Location(1, 0)));
    }

    @Test
    public void GivenStateWithFirstTwoPiecesInPlace_shouldReturnTheThirdLocation() {
        final GameState gs = GameState.empty()
                .put(new Player(), new Location(0, 0))
                .put(new Player(), new Location(1, 0));

        final Location played = interactor.play(gs);

        assertThat(played, is(new Location(2, 0)));
    }

    @Test
    public void GivenStateWithFirstThreePiecesInPlace_shouldReturnTheFourthLocation() {
        final GameState gs = GameState.empty()
                .put(new Player(), new Location(0, 0))
                .put(new Player(), new Location(1, 0))
                .put(new Player(), new Location(2, 0));

        final Location played = interactor.play(gs);

        assertThat(played, is(new Location(0, 1)));
    }

    @Test (expected = IllegalArgumentException.class)
    public void GivenStateWithAllPiecesInPlace_shouldThrowException() {
        final GameState gs = GameState.empty()
                .put(new Player(), new Location(0, 0))
                .put(new Player(), new Location(1, 0))
                .put(new Player(), new Location(2, 0))
                .put(new Player(), new Location(0, 1))
                .put(new Player(), new Location(1, 1))
                .put(new Player(), new Location(2, 1))
                .put(new Player(), new Location(0, 2))
                .put(new Player(), new Location(1, 2))
                .put(new Player(), new Location(2, 2));

        interactor.play(gs);
    }
}
