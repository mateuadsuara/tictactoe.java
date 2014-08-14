package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.game.State;
import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;

import java.util.Map;

public class TextRenderer implements Renderer<String> {
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

    public TextRenderer(Map<Player, Character> playerIconMappings){
        this.playerIconMappings = playerIconMappings;
    }

    public String render(State state) {
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

    private String[] getRenderedPieces(State state) {
        String[] renderedPieces = new String[LOCATIONS_IN_RENDERING_ORDER.length];

        int i = 0;
        for (Location l : LOCATIONS_IN_RENDERING_ORDER){
            renderedPieces[i] = renderPiece(state.lookAt(l));
            i++;
        }

        return renderedPieces;
    }

    private String renderPiece(Player player) {
        if (player == null)
            return " ";

        return playerIconMappings.get(player).toString();
    }
}
