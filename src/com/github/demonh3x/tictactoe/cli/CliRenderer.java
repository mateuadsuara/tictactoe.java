package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;
import com.github.demonh3x.tictactoe.Piece;
import com.github.demonh3x.tictactoe.Player;

public class CliRenderer {
    private final Player xOwner;
    private final Player oOwner;

    public CliRenderer(Player xOwner, Player oOwner){
        this.xOwner = xOwner;
        this.oOwner = oOwner;
    }

    public void render(GameState state){
        System.out.println(renderState(state));
    }

    private String renderState(GameState state) {
        return String.format(
                "   x 0   1   2\n" +
                " y +---+---+---+\n" +
                " 0 | %s | %s | %s |\n" +
                "   +---+---+---+\n" +
                " 1 | %s | %s | %s |\n" +
                "   +---+---+---+\n" +
                " 2 | %s | %s | %s |\n" +
                "   +---+---+---+",
                getRenderedPieces(state)
        );
    }

    private String[] getRenderedPieces(GameState state) {
        return new String[]{
                    getRenderedPiece(state, 0, 0),
                    getRenderedPiece(state, 1, 0),
                    getRenderedPiece(state, 2, 0),
                    getRenderedPiece(state, 0, 1),
                    getRenderedPiece(state, 1, 1),
                    getRenderedPiece(state, 2, 1),
                    getRenderedPiece(state, 0, 2),
                    getRenderedPiece(state, 1, 2),
                    getRenderedPiece(state, 2, 2)
            };
    }

    private String getRenderedPiece(GameState state, int x, int y) {
        return renderPiece(state.lookAt(new Location(x, y)));
    }

    private String renderPiece(Piece piece) {
        if (piece == null)
            return " ";

        if (piece.isOwnedBy(xOwner))
            return "X";

        if (piece.isOwnedBy(oOwner))
            return "O";

        throw new IllegalArgumentException();
    }
}
