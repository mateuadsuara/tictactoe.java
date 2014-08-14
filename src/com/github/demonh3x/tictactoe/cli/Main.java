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

        final Interactor cliInteractor = new HumanCliInteractor(xPlayer, System.out, System.in);
        final Interactor aiInteractor = new FirstPossiblePlayInteractor(oPlayer);

        List<Interactor> playerOrder = new LinkedList<>();
        playerOrder.add(cliInteractor);
        playerOrder.add(aiInteractor);
        final Iterator<Interactor> players = new CyclingIterator<>(playerOrder);

        final State initialState = State.empty();
        final StateIterator iterator = new StateIterator(initialState, players);

        notify(observers, initialState);
        while(iterator.hasNext()){
            final State state = iterator.next();
            notify(observers, state);
            if (new Logic(state).isFinished()) break;
        }
    }

    private static void notify(List<Observer> observers, State state) {
        for (Observer o : observers){
            o.notify(state);
        }
    }
}
