package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.*;
import com.github.demonh3x.tictactoe.ai.FirstPossiblePlayInteractor;

import java.util.*;

public class Main {
    public static void main(String ... args){
        final Player xPlayer = new Player();
        final Player oPlayer = new Player();

        final Map<Player, Character> mappings = new HashMap<>();
        mappings.put(xPlayer, 'X');
        mappings.put(oPlayer, 'O');

        final List<GameObserver> observers = new ArrayList<>();
        final CliObserver cliObserver = new CliObserver(System.out, new TextRenderer(mappings));
        observers.add(cliObserver);

        final GameInteractor cliInteractor = new HumanCliInteractor(System.out, System.in);
        final GameInteractor aiInteractor = new FirstPossiblePlayInteractor();

        final Map<Player, GameInteractor> interactors = new HashMap<>();
        interactors.put(xPlayer, cliInteractor);
        interactors.put(oPlayer, aiInteractor);

        List<Player> playerOrder = new LinkedList<>();
        playerOrder.add(xPlayer);
        playerOrder.add(oPlayer);
        final Iterator<Player> players = new CyclingIterator<>(playerOrder);

        final GameState initialState = GameState.empty();
        final GameIterator iterator = new GameIterator(initialState, interactors, players);

        update(observers, initialState);
        while(iterator.hasNext()){
            final GameState state = iterator.next();
            update(observers, state);
        }
    }

    private static void update(List<GameObserver> observers, GameState gameState) {
        for (GameObserver o : observers){
            o.update(gameState);
        }
    }
}
