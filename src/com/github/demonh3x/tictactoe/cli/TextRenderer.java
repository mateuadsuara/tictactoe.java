package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.GameState;
import com.github.demonh3x.tictactoe.Location;
import com.github.demonh3x.tictactoe.Piece;
import com.github.demonh3x.tictactoe.Player;

import java.util.Map;

public class TextRenderer {
    private static final Location[] LOCATIONS_IN_RENDERING_ORDER = {
            new Location(0, 0),
            new Location(1, 0),
            new Location(2, 0),
            new Location(0, 1),
            new Location(1, 1),
            new Location(2, 1),
            new Location(0, 2),
            new Location(1, 2),
            new Location(2, 2)
    };

    private final Map<Player, Character> playerIconMappings;
    private final GameState state;

    public TextRenderer(Map<Player, Character> playerIconMappings, GameState state){
        this.playerIconMappings = playerIconMappings;
        this.state = state;
    }

    public String render() {
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
        String[] renderedPieces = new String[LOCATIONS_IN_RENDERING_ORDER.length];

        int i = 0;
        for (Location l : LOCATIONS_IN_RENDERING_ORDER){
            renderedPieces[i] = renderPiece(state.lookAt(l));
            i++;
        }

        return renderedPieces;
    }

    private String renderPiece(Piece piece) {
        if (piece == null)
            return " ";

        for (Map.Entry<Player, Character> playerIconMapping : playerIconMappings.entrySet()){
            Player player = playerIconMapping.getKey();
            Character icon = playerIconMapping.getValue();

            if (piece.isOwnedBy(player))
                return icon.toString();
        }

        throw new IllegalArgumentException();
    }
}
