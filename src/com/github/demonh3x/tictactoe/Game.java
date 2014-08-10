package com.github.demonh3x.tictactoe;

import java.util.List;
import java.util.Map;

public class Game {
    private final List<GameObserver> observers;

    public Game(Map<Player, GameInteractor> interactors, List<GameObserver> observers) {
        this.observers = observers;
    }

    public void run() {
        for (GameObserver observer : observers)
            observer.update(GameState.empty());
    }
}
