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

        final List<Observer> observers = Arrays.<Observer>asList(
                new CliObserver(System.out, new TextRenderer(mappings))
        );

        final Iterator<Interactor> interactors = new CyclingIterator<>(Arrays.asList(
                new HumanCliInteractor(xPlayer, System.out, System.in),
                new FirstPossiblePlayInteractor(oPlayer)
        ));

        final State initialState = State.empty();
        final StateIterator iterator = new StateIterator(initialState, interactors);

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
