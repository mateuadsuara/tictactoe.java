package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.game.Logic;
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
        displayState(state);

        if (isFinished(state))
            displayResolution(state);
    }

    private void displayState(State state) {
        output.println(renderer.render(state));
    }

    private boolean isFinished(State state) {
        return new Logic(state).isFinished();
    }

    private void displayResolution(State state) {
        final Player winner = getWinner(state);

        if (winner != null)
            output.println(getIcon(winner) + " has won!");
        else
            output.println("Draw!");
    }

    private String getIcon(Player winner) {
        return playerIconMappings.get(winner).toString();
    }

    private Player getWinner(State state) {
        final Logic logic = new Logic(state);

        for (Player player : playerIconMappings.keySet()){
            if (logic.hasWon(player))
                return player;
        }

        return null;
    }
}
