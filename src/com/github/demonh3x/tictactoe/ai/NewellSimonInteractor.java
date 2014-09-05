package com.github.demonh3x.tictactoe.ai;

import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOption;
import com.github.demonh3x.tictactoe.ai.NewellSimon.MoveOptions.*;
import com.github.demonh3x.tictactoe.game.*;

import java.util.Arrays;
import java.util.List;

public class NewellSimonInteractor implements Interactor {
    private final Player representedPlayer;
    private final Player opponent;

    public NewellSimonInteractor(Player representedPlayer, Player opponent) {
        this.representedPlayer = representedPlayer;
        this.opponent = opponent;
    }

    @Override
    public Play play(State state) {
        return new Play(representedPlayer, new LocationDecision(representedPlayer, opponent, state).get());
    }

    private static class LocationDecision {
        private final Player player;
        private final Player opponent;
        private final State state;

        public LocationDecision(Player player, Player opponent, State state) {
            this.player = player;
            this.opponent = opponent;
            this.state = state;
        }

        public Location get() {
            final List<MoveOption> moveOptions = Arrays.asList(
                    new WinOption(state, player),
                    new BlockOption(state, opponent),
                    new ForkOption(state, player),
                    new BlockForkOption(state, player, opponent),
                    new CenterOption(state),
                    new OppositeCornerOption(state, opponent),
                    new CornerOption(state),
                    new SideOption(state)
            );

            for (MoveOption option : moveOptions)
                if (option.isAvailable())
                    return option.getLocation();

            throw new RuntimeException("Unhandled possibility!");
        }
    }
}
