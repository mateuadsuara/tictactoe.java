package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.*;

public class MinmaxInteractor implements Interactor {
    @Override
    public Play play(State state) {
        GameTree gameTree = new GameTreeGenerator(Player.X, Player.O).generate(state);
        Location location = gameTree.bestStrategy().locations.iterator().next();
        return new Play(null, location);
    }
}
