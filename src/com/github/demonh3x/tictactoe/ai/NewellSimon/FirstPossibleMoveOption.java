package com.github.demonh3x.tictactoe.ai.NewellSimon;

import com.github.demonh3x.tictactoe.game.Location;

import java.util.List;

public class FirstPossibleMoveOption {
    private final List<MoveOption> moveOptions;

    public FirstPossibleMoveOption(List<MoveOption> moveOptions) {
        this.moveOptions = moveOptions;
    }

    public Location get() {
        for (MoveOption option : moveOptions)
            if (option.isAvailable())
                return option.getLocation();

        throw new RuntimeException("Unhandled possibility!");
    }
}
