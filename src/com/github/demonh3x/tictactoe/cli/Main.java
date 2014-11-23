package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.ai.interactors.NewellSimonInteractor;
import com.github.demonh3x.tictactoe.game.*;
import com.github.demonh3x.tictactoe.game.Observer;

import java.util.*;

import static com.github.demonh3x.tictactoe.game.Player.*;

public class Main {
    public static void main(String ... args){
        final Map<Player, Character> mappings = new HashMap<>();
        mappings.put(X, 'X');
        mappings.put(O, 'O');

        final List<Observer> observers = Arrays.<Observer>asList(
                new CliObserver(System.out, mappings)
        );

        final State initialState = State.empty(new TicTacToeBoard());
        final StateIterator iterator = new StateIterator(
                initialState,
                new CyclingIterator<>(askPlayingOrder(X, O))
        );

        notify(observers, initialState);
        while(iterator.hasNext()){
            final State state = iterator.next();
            notify(observers, state);
            if (new Logic(state).isFinished()) break;
        }
    }

    private static List<Interactor> askPlayingOrder(Player xPlayer, Player oPlayer) {
        final List<Interactor> playingOrder;
        if (userWantsToPlayFirst()){
            playingOrder = Arrays.asList(
                    new HumanCliInteractor(System.out, System.in),
                    new NewellSimonInteractor(oPlayer, xPlayer)
            );
        } else {
            playingOrder = Arrays.asList(
                    new NewellSimonInteractor(xPlayer, oPlayer),
                    new HumanCliInteractor(System.out, System.in)
            );
        }
        return playingOrder;
    }

    private static void notify(List<Observer> observers, State state) {
        for (Observer o : observers){
            o.notify(state);
        }
    }

    private static boolean userWantsToPlayFirst() {
        return new Asker(System.out, System.in)
                .askForOneOption("Do you want to play first?", Arrays.asList("y", "n"))
                .equals("y");
    }
}
