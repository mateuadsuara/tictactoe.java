package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logic {
    private static final List<List<Location>> POSSIBLE_LINES = Arrays.asList(
            Arrays.asList(new Location(0, 0), new Location(1, 0), new Location(2, 0)),
            Arrays.asList(new Location(0, 1), new Location(1, 1), new Location(2, 1)),
            Arrays.asList(new Location(0, 2), new Location(1, 2), new Location(2, 2)),

            Arrays.asList(new Location(0, 0), new Location(0, 1), new Location(0, 2)),
            Arrays.asList(new Location(1, 0), new Location(1, 1), new Location(1, 2)),
            Arrays.asList(new Location(2, 0), new Location(2, 1), new Location(2, 2)),

            Arrays.asList(new Location(0, 0), new Location(1, 1), new Location(2, 2)),
            Arrays.asList(new Location(2, 0), new Location(1, 1), new Location(0, 2))
    );

    private final State state;

    public Logic(State state) {
        this.state = state;
    }

    public boolean isFinished() {
        return isFull() || hasALine();
    }

    private boolean isFull() {
        for (Location l : Location.getAll())
            if (state.lookAt(l) == null)
                return false;

        return true;
    }

    private boolean hasALine() {
        return getWinningLine() != null;
    }

    private List<Player> getWinningLine() {
        for (List<Location> line : POSSIBLE_LINES)
            if (isAWinningLine(getPiecesInTheLine(line)))
                return getPiecesInTheLine(line);

        return null;
    }

    private boolean isAWinningLine(List<Player> piecesInTheLine) {
        Player firstPiece = piecesInTheLine.get(0);

        if (firstPiece == null)
            return false;

        for (Player p : piecesInTheLine)
            if (p != firstPiece)
                return false;

        return true;
    }

    private List<Player> getPiecesInTheLine(List<Location> line) {
        ArrayList<Player> linePieces = new ArrayList<>(line.size());

        for (Location location : line)
            linePieces.add(state.lookAt(location));

        return linePieces;
    }

    public boolean hasWon(Player possibleWinner) {
        List<Player> winningLine = getWinningLine();

        return winningLine != null &&
                winningLine.get(0).equals(possibleWinner);
    }
}
