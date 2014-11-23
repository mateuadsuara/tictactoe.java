package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import org.junit.Test;

import java.util.Collections;

public class ChoiceTest {
    @Test (expected = IllegalArgumentException.class)
    public void CannotInstantiateWithoutSubTrees() {
        new Choice(Player.X, Collections.<Location, GameTree>emptyMap());
    }
}