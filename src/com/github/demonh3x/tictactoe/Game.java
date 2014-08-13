package com.github.demonh3x.tictactoe;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private final LinkedHashMap<Player, GameInteractor> interactors;
    private final List<GameObserver> observers;
    private GameState state;

    public Game(Map<Player, GameInteractor> interactors, List<GameObserver> observers) {
        this.interactors = new LinkedHashMap<>(interactors);
        this.observers = observers;
        this.state = GameState.empty();
    }

    public void run() {
        for (GameObserver observer : observers)
            observer.update(state);

        final Map.Entry<Player, GameInteractor> interactorEntry = interactors.entrySet().iterator().next();
        final GameInteractor interactor = interactorEntry.getValue();
        Location loc = interactor.play(state);

        final Player player = interactorEntry.getKey();
        state = state.put(player, loc);
    }
}
