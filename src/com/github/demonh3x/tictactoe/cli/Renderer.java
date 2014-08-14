package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.State;

public interface Renderer<T>{
    public T render(State state);
}
