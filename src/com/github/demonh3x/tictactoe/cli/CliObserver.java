package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameObserver;
import com.github.demonh3x.tictactoe.GameState;

import java.io.PrintStream;

public class CliObserver implements GameObserver {
    private final PrintStream output;
    private final Renderer<String> renderer;

    public CliObserver(PrintStream output, Renderer<String> renderer){
        this.output = output;
        this.renderer = renderer;
    }

    @Override
    public void update(GameState state) {
        output.println(renderer.render(state));
    }
}
