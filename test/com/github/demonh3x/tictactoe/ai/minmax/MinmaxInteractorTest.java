package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.demonh3x.tictactoe.game.Player.O;
import static com.github.demonh3x.tictactoe.game.Player.X;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinmaxInteractorTest {
    private static final Player _ = null;

    private void assertPlayedLocation(State state, Location location) {
        assertThat(play(state), is(location));
    }

    private Location play(State state) {
        Interactor interactor = new MinmaxInteractor();
        return interactor.choose(state);
    }

    private void assertPlayedOneOfLocationList(State state, List<Location> expectedOneOfThis){
        assertThat(expectedOneOfThis, hasItem(play(state)));
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
                        _, X, O,
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
                        _, X, _,
                        X, _, _,
                        O, O, _
                ),
                new Location(2, 2)
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
        assertPlayedOneOfLocationList(
                StateLiteral.create(
                        X, O, X,
                        _, _, O,
                        _, _, _
                ),
                Arrays.asList(
                        new Location(1, 1),
                        new Location(0, 2)
                )
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

    @Test
    public void GivenThePossibilityToBlockAFork_ShouldDoIt() {
        assertPlayedLocation(
                StateLiteral.create(
                        X, O, X,
                        _, _, _,
                        _, _, _
                ),
                new Location(1,1)
        );
    }

    @Test
    public void GivenTwoPossibleForksForTheOpponent_ShouldAttackAvoidingTheCreationOfTheFork() {
        assertPlayedOneOfLocationList(
                StateLiteral.create(
                        _, _, X,
                        _, O, _,
                        X, _, _
                ),
                Arrays.asList(
                        new Location(1, 0),
                        new Location(0, 1),
                        new Location(1, 2),
                        new Location(2, 1)
                )
        );
        assertPlayedLocation(
                StateLiteral.create(
                        X, O, _,
                        _, _, X,
                        _, _, _
                ),
                new Location(1,1)
        );
    }

    @Test
    public void GivenTheCenterTakenAndFreeCorners_ShouldTakeOneCorner() {
        assertPlayedOneOfLocationList(
                StateLiteral.create(
                        _, _, _,
                        _, X, _,
                        _, _, _
                ),
                Arrays.asList(
                        new Location(0, 0),
                        new Location(0, 2),
                        new Location(2, 2),
                        new Location(2, 0)
                )
        );
    }

    @Test
    public void GivenAllTheCornersAndCenterTaken_ShouldTakeAnEmptySide() {
        assertPlayedLocation(
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, _, O
                ),
                new Location(1, 2)
        );
    }
}
