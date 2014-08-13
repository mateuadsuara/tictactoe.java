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

        gameState = GameState.empty();

        observers = new ArrayList<>();
        final CliObserver cliObserver = new CliObserver(System.out, new TextRenderer(mappings));
        observers.add(cliObserver);

        final GameInteractor cliInteractor = new HumanCliInteractor(System.out, System.in);
        final GameInteractor aiInteractor = new FirstPossiblePlayInteractor();

        interactors = new HashMap<>();
        interactors.put(xPlayer, cliInteractor);
        interactors.put(oPlayer, aiInteractor);

        List<Player> playerOrder = new LinkedList<>();
        playerOrder.add(xPlayer);
        playerOrder.add(oPlayer);
        players = new CyclingIterator<>(playerOrder);

        run();
    }

    private static Map<Player, GameInteractor> interactors;
    private static GameState gameState;
    private static List<GameObserver> observers;
    private static Iterator<Player> players;

    private static void start() {
        update();
    }

    private static void run() {
        start();
        while(!isFinished()){
            tick();
        }
    }

    private static boolean isFinished() {
        return new GameLogic(gameState).isFinished() || !players.hasNext();
    }

    private static void tick() {
        gameState = askToPlay(players.next());
        update();
    }

    private static void update() {
        for (GameObserver o : observers){
            o.update(gameState);
        }
    }

    private static GameState askToPlay(Player p) {
        return gameState.put(p, interactors.get(p).play(gameState));
    }
}
