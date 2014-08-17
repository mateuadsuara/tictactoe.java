package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewellSimonInteractorTest {
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
        interactor = new NewellSimonInteractor(X, O);
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

    @Test
    public void GivenThePossibilityToLose_ShouldBlock() {
        assertPlayedLocation(
                StateLiteral.create(
                        X, _, _,
                        O, _, O,
                        X, _, _
                ),
                new Location(1, 1)
        );
        assertPlayedLocation(
                StateLiteral.create(
                        X, _, _,
                        X, _, _,
                        O, _, O
                ),
                new Location(1, 2)
        );
    }

    @Test
    public void GivenThePossibilityToWin_ShouldPreferItOverBlocking() {
        assertPlayedLocation(
                StateLiteral.create(
                        X, _, O,
                        _, _, O,
                        X, _, _
                ),
                new Location(0, 1)
        );
    }

    @Test
    public void GivenThePossibilityToFork_ShouldDoIt() {
        assertPlayedLocation(
                StateLiteral.create(
                        X, O, X,
                        _, _, O,
                        _, _, _
                ),
                new Location(1, 1)
        );
    }

    @Test
    public void GivenThePossibilityToBlock_ShouldPreferItOverForking() {
        assertPlayedLocation(
                StateLiteral.create(
                        _, _, X,
                        _, O, _,
                        X, O, _
                ),
                new Location(1, 0)
        );
    }
}
