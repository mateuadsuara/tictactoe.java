package com.github.demonh3x.tictactoe.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Logic {
    private final State state;

    public Logic(State state) {
        this.state = state;
    }

    public boolean isFinished() {
        return isFull() || hasALine();
    }

    private boolean isFull() {
        for (Location l : state.board.getAllLocations())
            if (state.lookAt(l) == null)
                return false;

        return true;
    }

    private boolean hasALine() {
        return getWinningLine() != null;
    }

    private List<Player> getWinningLine() {
        for (Collection<Location> line : state.board.getPossibleLines())
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

    private List<Player> getPiecesInTheLine(Collection<Location> line) {
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
