package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameObserver;
import com.github.demonh3x.tictactoe.GameState;

public class CliObserver implements GameObserver {
    private final Renderer<String> renderer;

    public CliObserver(Renderer<String> renderer){
        this.renderer = renderer;
    }

    @Override
    public void update(GameState state) {
        print(renderer.render(state));
    }

    private void print(String message){
        System.out.println(message);
    }
}
