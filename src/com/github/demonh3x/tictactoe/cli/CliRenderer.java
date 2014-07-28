package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;
import com.github.demonh3x.tictactoe.Piece;
import com.github.demonh3x.tictactoe.Player;

public class CliRenderer {
    public static final String WALL = "|";
    public static final String FLOOR = "+-+-+-+\n";

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
        String renderedState = FLOOR;
        for (int x = 0; x < GameState.ROWS; x++){
            renderedState += renderRow(state, x) + "\n" + FLOOR;
        }
        return renderedState;
    }

    private String renderRow(GameState state, int x) {
        String row = WALL;
        for (int y = 0; y < GameState.COLUMNS; y++){
            final Piece piece = state.lookAt(new Location(x, y));
            final String renderedPiece = renderPiece(piece);
            row += renderedPiece + WALL;
        }
        return row;
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
