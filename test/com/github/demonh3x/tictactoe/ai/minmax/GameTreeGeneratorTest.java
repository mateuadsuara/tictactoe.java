package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.StateLiteral;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import static com.github.demonh3x.tictactoe.game.Player.*;

public class GameTreeGeneratorTest {
    private static final Player _ = null;

    private void assertGameTree(State state, GameTree tree) {
        GameTreeGenerator generator = new GameTreeGenerator(X, O);
        assertThat(generator.generate(state), is(tree));
    }

    @Test
    public void GivenAWonState_ShouldGenerateALeafNodeWithTheWinner() {
        assertGameTree(
                StateLiteral.create(
                        X, X, X,
                        O, O, _,
                        _, _, _
                ),
                WinningOutcome.get()
        );

        assertGameTree(
                StateLiteral.create(
                        X, X, _,
                        O, O, O,
                        X, _, _
                ),
                WinningOutcome.get()
        );
    }

    @Test
    public void GivenADrawState_ShouldGenerateALeafNodeWithoutAWinner() {
        assertGameTree(
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, X, O
                ),
                DrawOutcome.get()
        );
    }

    @Test
    public void GivenAStateWithOnlyOnePlayAvailable_ShouldGenerateAOneLevelTree() {
        assertGameTree(
                StateLiteral.create(
                        X, X, O,
                        O, O, X,
                        X, _, O
                ),
                new Choice(new Location(1, 2), DrawOutcome.get())
        );
        assertGameTree(
                StateLiteral.create(
                        O, X, O,
                        _, X, O,
                        X, O, X
                ),
                new Choice(new Location(0, 1), DrawOutcome.get())
        );
        assertGameTree(
                StateLiteral.create(
                        O, X, O,
                        _, X, X,
                        X, O, O
                ),
                new Choice(new Location(0, 1), WinningOutcome.get())
        );
    }

    @Test
    public void GivenThreeChoicesFinishingTheGame_ShouldGenerateAOneLevelTree() {
        assertGameTree(
                StateLiteral.create(
                        X, O, _,
                        _, X, O,
                        X, O, _
                ),
                new Choice(new HashMap<Location, GameTree>(){{
                    put(new Location(2, 0), WinningOutcome.get());
                    put(new Location(0, 1), WinningOutcome.get());
                    put(new Location(2, 2), WinningOutcome.get());
                }})
        );
    }

    @Test
    public void GivenAExtensibleSituation_ShouldGenerateAMultiLevelTree() {
        assertGameTree(
                StateLiteral.create(
                        X, X, O,
                        _, O, O,
                        X, _, X
                ),
                new Choice(new HashMap<Location, GameTree>(){{
                    put(new Location(0, 1), WinningOutcome.get());
                    put(new Location(1, 2), new Choice(new Location(0, 1), WinningOutcome.get()));
                }})
        );
    }
}
