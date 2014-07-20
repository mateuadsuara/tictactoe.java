package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class GameStateTest {
    private static final Piece xPieceInstance = new Piece();
    private static final Piece oPieceInstance = new Piece();

    private static final Supplier<Piece> _ = () -> null;
    private static final Supplier<Piece> X = () -> xPieceInstance;
    private static final Supplier<Piece> O = () -> oPieceInstance;

    @SafeVarargs
    private final GameState createGameState(Supplier<Piece>... suppliers) {
        List<Piece> pieces = Arrays.asList(suppliers).stream()
                .map((pieceSupplier) -> pieceSupplier.get())
                .collect(Collectors.toList());

        return new GameState(pieces.toArray(new Piece[suppliers.length]));
    }

    public class IsNotFinished {
        private void assertIsNotFinished(GameState gs) {
            assertThat(gs.isFinished(), is(false));
        }

        @Test
        public void GivenAnEmptyGameState() {
            assertIsNotFinished(createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void GivenSomePiecesWithoutAnyLine() {
            assertIsNotFinished(createGameState(
                    _, _, _,
                    _, X, O,
                    X, O, X
            ));
        }
    }

    public class IsFinished {
        private void assertIsFinished(GameState gs) {
            assertThat(gs.isFinished(), is(true));
        }

        @Test
        public void GivenAFullGameStateWitoutLines() {
            assertIsFinished(createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            ));
        }

        @Test
        public void GivenALine() {
            assertIsFinished(createGameState(
                    _, _, _,
                    O, _, O,
                    X, X, X
            ));
        }
    }
}
