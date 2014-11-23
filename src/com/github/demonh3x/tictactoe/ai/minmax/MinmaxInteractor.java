package com.github.demonh3x.tictactoe.ai.minmax;

import com.github.demonh3x.tictactoe.game.*;

public class MinmaxInteractor implements Interactor {
    @Override
    public Location choose(State state) {
        GameTree gameTree = new GameTreeGenerator(Player.X, Player.O).generate(state);
        return gameTree.bestStrategy().locations.iterator().next();
    }
}
