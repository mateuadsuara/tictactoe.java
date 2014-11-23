package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameTreeTest {
    private void assertStrategy(GameTree choice, Strategy strategy) {
        assertThat(choice.bestStrategy(), is(strategy));
    }

    @Test
    public void GivenImmediateOutcomes_ShouldPreferWinning() {
        assertStrategy(
                new Choice(new HashMap<Location, GameTree>() {{
                    put(new Location(0, 0), WinningOutcome.get());
                    put(new Location(1, 1), DrawOutcome.get());
                }}),
                new Strategy(1, new Location(0, 0))
        );
        assertStrategy(
                new Choice(new HashMap<Location, GameTree>() {{
                    put(new Location(0, 1), WinningOutcome.get());
                    put(new Location(1, 1), DrawOutcome.get());
                }}),
                new Strategy(1, new Location(0, 1))
        );
    }

    @Test
    public void GivenImmediateOutcomesForTheOther_ShouldSupposeWouldPreferWinning() {
        assertStrategy(
                new Choice(new HashMap<Location, GameTree>() {{
                    put(new Location(1, 0), WinningOutcome.get());
                    put(new Location(1, 1), DrawOutcome.get());
                }}),
                new Strategy(1, new Location(1, 0))
        );
    }

    @Test
    public void GivenATwoLevelChoiceTree_ShouldPreferDrawOverADefeat() {
        assertStrategy(
                new Choice(new HashMap<Location, GameTree>() {{
                    put(new Location(1, 0), new Choice(new Location(1, 1), WinningOutcome.get()));
                    put(new Location(1, 1), DrawOutcome.get());
                }}),
                new Strategy(0, new Location(1, 1))
        );
    }

    @Test
    public void GivenATwoLevelChoiceTree_ShouldPreferWinningOverADraw() {
        assertStrategy(
                new Choice(new HashMap<Location, GameTree>() {{
                    put(new Location(1, 0), new Choice(new HashMap<Location, GameTree>() {{
                        put(new Location(1, 1), DrawOutcome.get());
                    }}));
                    put(new Location(1, 1), WinningOutcome.get());
                }}),
                new Strategy(1, new Location(1, 1))
        );
    }

    @Test
    public void GivenALeaf_ShouldEvaluateToAnEmptyStrategy() {
        assertStrategy(
                DrawOutcome.get(),
                new Strategy(0)
        );
    }
}
