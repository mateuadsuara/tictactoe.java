package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;
import com.github.demonh3x.tictactoe.Player;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String ... args){
        final Player xPlayer = new Player();
        final Player oPlayer = new Player();

        final Map<Player, Character> mappings = new HashMap<>();
        mappings.put(xPlayer, 'X');
        mappings.put(oPlayer, 'O');

        final GameState gameState = GameState.empty()
                .put(xPlayer, new Location(0, 0))
                .put(oPlayer, new Location(1, 0));

        final CliObserver cliObserver = new CliObserver(System.out, new TextRenderer(mappings));
        final HumanCliInteractor cliInteractor = new HumanCliInteractor(System.out, System.in);

        cliObserver.update(gameState);
        cliInteractor.play(gameState);
    }
}
