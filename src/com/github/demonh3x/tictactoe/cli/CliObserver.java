package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.game.Observer;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.io.PrintStream;
import java.util.Map;

public class CliObserver implements Observer {
    private final PrintStream output;
    private final Map<Player, Character> playerIconMappings;
    private final TextRenderer renderer;

    public CliObserver(PrintStream output, Map<Player, Character> playerIconMappings){
        this.output = output;
        this.playerIconMappings = playerIconMappings;
        this.renderer = new TextRenderer(playerIconMappings);
    }

    @Override
    public void notify(State state) {
        output.println(renderer.render(state));
    }
}
