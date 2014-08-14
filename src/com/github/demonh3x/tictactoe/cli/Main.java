package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.game.*;
import com.github.demonh3x.tictactoe.ai.FirstPossiblePlayInteractor;
import com.github.demonh3x.tictactoe.game.Observer;

import java.util.*;

public class Main {
    public static void main(String ... args){
        final Player xPlayer = new Player();
        final Player oPlayer = new Player();

        final Map<Player, Character> mappings = new HashMap<>();
        mappings.put(xPlayer, 'X');
        mappings.put(oPlayer, 'O');

        final List<Observer> observers = new ArrayList<>();
        final CliObserver cliObserver = new CliObserver(System.out, new TextRenderer(mappings));
        observers.add(cliObserver);

        final Interactor cliInteractor = new HumanCliInteractor(System.out, System.in);
        final Interactor aiInteractor = new FirstPossiblePlayInteractor();

        final Map<Player, Interactor> interactors = new HashMap<>();
        interactors.put(xPlayer, cliInteractor);
        interactors.put(oPlayer, aiInteractor);

        List<Player> playerOrder = new LinkedList<>();
        playerOrder.add(xPlayer);
        playerOrder.add(oPlayer);
        final Iterator<Player> players = new CyclingIterator<>(playerOrder);

        final State initialState = State.empty();
        final StateIterator iterator = new StateIterator(initialState, interactors, players);

        update(observers, initialState);
        while(iterator.hasNext()){
            final State state = iterator.next();
            update(observers, state);
            if (new Logic(state).isFinished()) break;
        }
    }

    private static void update(List<Observer> observers, State state) {
        for (Observer o : observers){
            o.update(state);
        }
    }
}
