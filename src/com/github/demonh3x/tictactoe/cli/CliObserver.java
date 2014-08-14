package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.Observer;
import com.github.demonh3x.tictactoe.State;

import java.io.PrintStream;

public class CliObserver implements Observer {
    private final PrintStream output;
    private final Renderer<String> renderer;

    public CliObserver(PrintStream output, Renderer<String> renderer){
        this.output = output;
        this.renderer = renderer;
    }

    @Override
    public void update(State state) {
        output.println(renderer.render(state));
    }
}
