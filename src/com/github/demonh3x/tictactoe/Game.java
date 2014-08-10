package com.github.demonh3x.tictactoe;

import java.util.List;
import java.util.Map;

public class Game {
    private final Map<Player, GameInteractor> interactors;
    private final List<GameObserver> observers;

    public Game(Map<Player, GameInteractor> interactors, List<GameObserver> observers) {
        this.interactors = interactors;
        this.observers = observers;
    }

    public void run() {
        for (GameObserver observer : observers)
            observer.update(GameState.empty());

        interactors.entrySet().iterator().next().getValue().play(GameState.empty());
    }
}
