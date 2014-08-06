package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;

public interface Renderer<T>{
    public T render(GameState state);
}
