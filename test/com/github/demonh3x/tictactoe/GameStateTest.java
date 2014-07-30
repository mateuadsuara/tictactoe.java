package com.github.demonh3x.tictactoe;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(HierarchicalContextRunner.class)
public class GameStateTest {
    private static final Player xPlayer = new Player();
    private static final Player oPlayer = new Player();

    private static final Piece _ = null;
    private static final Piece X = new Piece(xPlayer);
    private static final Piece O = new Piece(oPlayer);

    private GameState createGameState(Piece... pieces) {
        final GameState gameState = GameState.empty();
        return gameState
                .put(pieces[0], new Location(0, 0))
                .put(pieces[1], new Location(1, 0))
                .put(pieces[2], new Location(2, 0))
                .put(pieces[3], new Location(0, 1))
                .put(pieces[4], new Location(1, 1))
                .put(pieces[5], new Location(2, 1))
                .put(pieces[6], new Location(0, 2))
                .put(pieces[7], new Location(1, 2))
                .put(pieces[8], new Location(2, 2));
    }

    final GameState[] gameStatesWithLineOfX = {
            createGameState(
                    X, X, X,
                    O, _, _,
                    O, _, _
            ),
            createGameState(
                    O, _, _,
                    X, X, X,
                    O, _, _
            ),
            createGameState(
                    O, _, _,
                    O, _, _,
                    X, X, X
            ),
            createGameState(
                    X, O, _,
                    X, O, _,
                    X, _, _
            ),
            createGameState(
                    O, X, _,
                    O, X, _,
                    _, X, _
            ),
            createGameState(
                    O, _, X,
                    O, _, X,
                    _, _, X
            ),
            createGameState(
                    O, _, X,
                    O, X, _,
                    X, _, _
            ),
            createGameState(
                    X, _, _,
                    O, X, _,
                    O, _, X
            )
    };

    public class LookAt {
        @Test
        public void anEmptyLocation_ReturnsNull() {
            final GameState gameState = createGameState(
                    _, X, X,
                    X, O, O,
                    O, O, X
            );
            assertThat(gameState.lookAt(new Location(0, 0)), is(_));
        }

        @Test
        public void aLocationContainingAXPiece_ReturnsAXPiece() {
            final GameState gameState = createGameState(
                    _, _, _,
                    _, X, _,
                    _, _, _
            );
            assertThat(gameState.lookAt(new Location(1, 1)), is(X));
        }

        @Test
        public void aLocationContainingAOPiece_ReturnsAOPiece() {
            final GameState gameState = createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, O
            );
            assertThat(gameState.lookAt(new Location(2, 2)), is(O));
        }


        @Test
        public void outside_ThrowsIllegalArgumentException() {
            assertIllegalLocation(new Location(-1, 0));
            assertIllegalLocation(new Location(3, 2));
        }

        private void assertIllegalLocation(Location l) {
            final GameState gameState = createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            );

            boolean exceptionThrown = false;
            try {
                gameState.lookAt(l);
            } catch (IllegalArgumentException e){
                exceptionThrown = true;
            }

            final String reason = String.format(
                    "Expected GameState.lookAt(Location(x:%s, y:%s)) to throw IllegalArgumentException",
                    l.x, l.y
            );
            assertThat(reason, exceptionThrown, is(true));
        }
    }

    public class PutPieceAtLocation {
        GameState originalGameState, newGameState;
        Location l;

        @Before
        public void setUp() {
            originalGameState = createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            );
            l = new Location(0, 0);
            newGameState = originalGameState.put(X, l);
        }

        @Test
        public void theOriginalGameState_shouldntHaveBeenModified() {
            assertThat(originalGameState.lookAt(l), is(_));
        }

        @Test
        public void theNewGameState_shouldHaveThePuttedPiece() {
            assertThat(newGameState.lookAt(l), is(X));
        }
    }

    public class IsNotFinished {
        private void assertIsNotFinished(GameState gs) {
            assertThat(new GameLogic(gs).isFinished(), is(false));
        }

        @Test
        public void givenAnEmptyGameState() {
            assertIsNotFinished(createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void givenAlmostFullGameWithoutAnyLine() {
            assertIsNotFinished(createGameState(
                    X, O, X,
                    O, _, O,
                    X, O, X
            ));
            assertIsNotFinished(createGameState(
                    X, _, X,
                    X, O, O,
                    O, X, O
            ));
        }
    }

    public class IsFinished {
        private void assertIsFinished(GameState... states) {
            for (GameState gs : states)
                assertThat(new GameLogic(gs).isFinished(), is(true));
        }

        @Test
        public void givenAFullGameStateWithoutLines() {
            assertIsFinished(createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            ));
        }

        @Test
        public void givenALine() {
            assertIsFinished(gameStatesWithLineOfX);
        }
    }

    public class ThereIsNoWinner {
        private void assertNoWinner(GameState gs) {
            Player[] players = {xPlayer, oPlayer};

            for (Player p : players)
                assertThat(new GameLogic(gs).hasWon(p), is(false));
        }

        @Test
        public void givenAnEmptyGameState() {
            assertNoWinner(createGameState(
                    _, _, _,
                    _, _, _,
                    _, _, _
            ));
        }

        @Test
        public void givenAFullGameStateWithoutLines() {
            assertNoWinner(createGameState(
                    X, X, O,
                    O, O, X,
                    X, X, O
            ));
        }
    }

    public class ThereIsAWinner {
        private void assertTheWinner(Player p, GameState... states){
            for (GameState gs : states)
                assertThat(new GameLogic(gs).hasWon(p), is(true));
        }

        @Test
        public void givenALineOfX() {
            assertTheWinner(xPlayer, gameStatesWithLineOfX);
        }

        @Test
        public void givenALineOfO() {
            assertTheWinner(oPlayer, createGameState(
                    O, O, O,
                    X, _, _,
                    X, _, _
            ));
        }
    }
}
