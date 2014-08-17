package com.github.demonh3x.tictactoe.ai.decision_making;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Logic;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatePossibilitiesForesighter {
    private final Player playerPlayingNext;

    public StatePossibilitiesForesighter(Player playerPlayingNext) {
        this.playerPlayingNext = playerPlayingNext;
    }

    public List<State> foresee(State initial){
        if (new Logic(initial).isFinished())
            return new ArrayList<>();
        else
            return Arrays.asList(initial.put(playerPlayingNext, new Location(1,2)));
    }
}
