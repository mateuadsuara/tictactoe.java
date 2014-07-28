package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;
import com.github.demonh3x.tictactoe.Piece;
import com.github.demonh3x.tictactoe.Player;

public class CliRenderer {
    private final Player xOwner;
    private final Player oOwner;
    private final GameState state;

    public CliRenderer(Player xOwner, Player oOwner, GameState state){
        this.xOwner = xOwner;
        this.oOwner = oOwner;
        this.state = state;
    }

    public void render(){
        System.out.println(renderState());
    }

    private String renderState() {
        return String.format(
                "   x 0   1   2\n" +
                " y +---+---+---+\n" +
                " 0 | %s | %s | %s |\n" +
                "   +---+---+---+\n" +
                " 1 | %s | %s | %s |\n" +
                "   +---+---+---+\n" +
                " 2 | %s | %s | %s |\n" +
                "   +---+---+---+",
                getRenderedPieces()
        );
    }

    private String[] getRenderedPieces() {
        return new String[]{
                    getRenderedPiece(0, 0),
                    getRenderedPiece(1, 0),
                    getRenderedPiece(2, 0),
                    getRenderedPiece(0, 1),
                    getRenderedPiece(1, 1),
                    getRenderedPiece(2, 1),
                    getRenderedPiece(0, 2),
                    getRenderedPiece(1, 2),
                    getRenderedPiece(2, 2)
            };
    }

    private String getRenderedPiece(int x, int y) {
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
