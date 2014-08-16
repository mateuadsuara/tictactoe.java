package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HardestInteractorTest {
    private static final Player _ = null;
    private static final Player X = new Player();
    private static final Player O = new Player();

    private void assertPlayedLocation(State state, Location location) {
        Play play = interactor.play(state);
        assertThat(play.location, is(location));
    }

    Interactor interactor;

    @Before
    public void setUp() {
        interactor = new HardestInteractor(X);
    }

    @Test
    public void GivenNoChoice_ShouldDoTheOnlyPlayAvailable() {
        assertPlayedLocation(
                StateLiteral.create(
                        X, O, X,
                        O, X, O,
                        O, X, _
                ),
                new Location(2, 2)
        );

        assertPlayedLocation(
                StateLiteral.create(
                        X, O, _,
                        O, X, X,
                        X, O, O
                ),
                new Location(2, 0)
        );

        assertPlayedLocation(
                StateLiteral.create(
                        _, X, O,
                        O, X, O,
                        X, O, X
                ),
                new Location(0, 0)
        );

        assertPlayedLocation(
                StateLiteral.create(
                        O, O, X,
                        X, X, O,
                        _, O, X
                ),
                new Location(0, 2)
        );

        assertPlayedLocation(
                StateLiteral.create(
                        X, O, X,
                        O, X, X,
                        O, _, O
                ),
                new Location(1, 2)
        );
    }

    @Test
    public void GivenTheChoiceToWin_ShouldWin() {
        assertPlayedLocation(
                StateLiteral.create(
                        X, O, X,
                        O, X, O,
                        O, _, _
                ),
                new Location(2, 2)
        );
    }
}
