package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameState {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;
    private static final int REQUIRED_AMOUNT_OF_PIECES = ROWS * COLUMNS;
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

    private final List<Piece> pieces;

    public GameState(List<Piece> pieces) {
        if (pieces.size() != REQUIRED_AMOUNT_OF_PIECES)
            throw new IllegalArgumentException("A game state should have " + REQUIRED_AMOUNT_OF_PIECES + " pieces!");

        this.pieces = Collections.unmodifiableList(pieces);
    }

    public Boolean isFinished() {
        return isFull() || hasALine();
    }

    private boolean isFull() {
        for (Piece p : pieces)
            if (p == null)
                return false;

        return true;
    }

    private boolean hasALine() {
        return getWinningLine() != null;
    }

    private List<Piece> getWinningLine() {
        for (List<Location> line : POSSIBLE_LINES)
            if (isAWinningLine(getPiecesInTheLine(line)))
                return getPiecesInTheLine(line);

        return null;
    }

    private boolean isAWinningLine(List<Piece> piecesInTheLine) {
        Piece firstPiece = piecesInTheLine.get(0);

        if (firstPiece == null)
            return false;

        for (Piece p : piecesInTheLine)
            if (p != firstPiece)
                return false;

        return true;
    }

    private List<Piece> getPiecesInTheLine(List<Location> line) {
        ArrayList<Piece> linePieces = new ArrayList<>(line.size());

        for (Location location : line)
            linePieces.add(lookAt(location));

        return linePieces;
    }

    public boolean hasWon(Player possibleWinner) {
        List<Piece> winningLine = getWinningLine();

        return winningLine != null &&
               winningLine.get(0).isOwnedBy(possibleWinner);
    }

    public Piece lookAt(Location l){
        return pieces.get(getIndex(l));
    }

    private int getIndex(Location l) {
        return (l.y * ROWS) + l.x;
    }

    public GameState put(Piece p, Location l) {
        final ArrayList<Piece> newPieces = new ArrayList<>(this.pieces);
        newPieces.set(getIndex(l), p);
        return new GameState(newPieces);
    }
}
