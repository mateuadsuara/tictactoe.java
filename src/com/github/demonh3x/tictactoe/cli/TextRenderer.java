package com.github.demonh3x.tictactoe.cli;

import com.github.demonh3x.tictactoe.game.Location;
import com.github.demonh3x.tictactoe.game.Player;
import com.github.demonh3x.tictactoe.game.State;

import java.util.*;

public class TextRenderer {
    private final Map<Player, Character> playerIconMappings;

    public TextRenderer(Map<Player, Character> playerIconMappings){
        this.playerIconMappings = playerIconMappings;
    }

    public String render(State state) {
        return String.format(
                generateBoard(state.board.getAllLocations()),
                getRenderedPieces(state)
        );
    }

    private Set<Integer> getColumns(List<Location> allLocations) {
        Set<Integer> columns = new HashSet<>();

        for (Location location : allLocations)
            columns.add(location.col);

        return columns;
    }

    private Set<Integer> getRows(List<Location> allLocations) {
        Set<Integer> rows = new HashSet<>();

        for (Location location : allLocations)
            rows.add(location.row);

        return rows;
    }

    private String generateBoard(List<Location> allLocations) {
        if (allLocations.size() == 0)
            return "";

        final Set<Integer> columns = getColumns(allLocations);
        final Set<Integer> rows = getRows(allLocations);

        String generated =
                "     " + generateColumnsHeader(columns) + "\n" +
                "   " + generateSeparator(columns.size());

        for (Integer row : rows) {
            generated += "\n";
            generated += " " + row + " " + generatePlaceHolderRow(columns.size()) + "\n";
            generated += "   " + generateSeparator(columns.size());
        }

        return generated;
    }

    private String generateColumnsHeader(Set<Integer> columns) {
        if (columns.size() == 0)
            return "";

        String header = "";

        for (Integer column : columns) {
            header += column + "   ";
        }

        return header.trim();
    }

    private String generateSeparator(int size) {
        if (size == 0)
            return "";

        String footer = "+";

        for (int col = 0; col < size; col++){
            footer += "---+";
        }

        return footer;
    }

    private String generatePlaceHolderRow(int size){
        if (size == 0)
            return "";

        String placeHolder = "|";

        for (int i = 0; i < size; i++){
            placeHolder += " %s |";
        }

        return placeHolder;
    }

    private String[] getRenderedPieces(State state) {
        List<Location> locations = new ArrayList<>(state.board.getAllLocations());
        Collections.sort(locations, new Comparator<Location>() {
            @Override
            public int compare(Location a, Location b) {
                if (a.row != b.row)
                    return a.row - b.row;
                return a.col - b.col;
            }
        });
        String[] renderedPieces = new String[locations.size()];

        int i = 0;
        for (Location l : locations){
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
