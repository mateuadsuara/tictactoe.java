package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.ai.interactors.NewellSimonInteractor;
import com.github.demonh3x.tictactoe.game.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NewellSimonInteractorTest {
    private static final Player _ = null;
    private static final Player X = new Player();
    private static final Player O = new Player();

    private void assertPlayedLocation(Player represented, State state, Location location) {
        Play play = play(represented, state);
        assertThat(play.player, is(represented));
        assertThat(play.location, is(location));
    }

    private Play play(Player represented, State state) {
        Player opponent = represented == X? O: X;
        Interactor interactor = new NewellSimonInteractor(represented, opponent);
        return interactor.play(state);
    }

    private void assertPlayedOneOfLocationList(Player represented, State state, List<Location> expectedOneOfThis){
        Play play = play(represented, state);
        assertThat(play.player, is(represented));
        assertThat(expectedOneOfThis, hasItem(play.location));
    }

    @Test
    public void GivenNoChoice_ShouldDoTheOnlyPlayAvailable() {
        assertPlayedLocation(
                X,
                StateLiteral.create(
                        X, O, X,
                        O, X, O,
                        O, X, _
                ),
                new Location(2, 2)
        );

        assertPlayedLocation(
                X,
                StateLiteral.create(
                        X, O, _,
                        O, X, X,
                        X, O, O
                ),
                new Location(2, 0)
        );

        assertPlayedLocation(
                X,
                StateLiteral.create(
                        _, X, O,
                        O, X, O,
                        X, O, X
                ),
                new Location(0, 0)
        );

        assertPlayedLocation(
                X,
                StateLiteral.create(
                        O, O, X,
                        X, X, O,
                        _, O, X
                ),
                new Location(0, 2)
        );

        assertPlayedLocation(
                X,
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
                X,
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
                X,
                StateLiteral.create(
                        X, _, _,
                        O, _, O,
                        X, _, _
                ),
                new Location(1, 1)
        );
        assertPlayedLocation(
                X,
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
                X,
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
                X,
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
                X,
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
                O,
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
                O,
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
                O,
                StateLiteral.create(
                        X, O, _,
                        _, _, X,
                        _, _, _
                ),
                new Location(1,1)
        );
    }

    @Test
    public void GivenAStateWithTheCenterAvailable_ShouldTakeIt() {
        assertPlayedLocation(
                X,
                StateLiteral.create(
                        _, _, _,
                        _, _, _,
                        _, _, _
                ),
                new Location(1, 1)
        );
    }

    @Test
    public void GivenAPlayerInTheCorner_ShouldPlayTheOppositeCorner() {
        assertPlayedLocation(
                X,
                StateLiteral.create(
                        O, _, _,
                        _, X, _,
                        _, _, _
                ),
                new Location(2, 2)
        );
        assertPlayedLocation(
                X,
                StateLiteral.create(
                        _, _, O,
                        _, X, _,
                        _, _, _
                ),
                new Location(0, 2)
        );
    }

    @Test
    public void GivenTheCenterAvailable_ShouldPreferItOverTheOppositeCorner() {
        assertPlayedLocation(
                O,
                StateLiteral.create(
                        X, _, _,
                        _, _, _,
                        _, _, _
                ),
                new Location(1, 1)
        );
    }

    @Test
    public void GivenTheCenterTakenAndFreeCorners_ShouldTakeOneCorner() {
        assertPlayedOneOfLocationList(
                O,
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
                X,
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, _, O
                ),
                new Location(1, 2)
        );
    }
}
