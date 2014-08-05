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
        final Location played = interactor.play(GameState.empty());
        assertThat(played, is(new Location(0, 0)));
    }
}
