package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameState {
    public static final int ROWS = 3;
    public static final int COLUMNS = 3;
    private static final int REQUIRED_AMOUNT_OF_PIECES = ROWS * COLUMNS;

    private final List<Piece> pieces;

    public GameState(List<Piece> pieces) {
        if (pieces.size() != REQUIRED_AMOUNT_OF_PIECES)
            throw new IllegalArgumentException("A game state should have " + REQUIRED_AMOUNT_OF_PIECES + " pieces!");

        this.pieces = Collections.unmodifiableList(pieces);
    }

    public Piece lookAt(Location l){
        final int index = getIndex(l);

        return pieces.get(index);
    }

    private int getIndex(Location l) {
        return (l.y * ROWS) + l.x;
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
        List<List<Integer>> possibleLines = Arrays.asList(
                Arrays.asList(0, 1, 2),
                Arrays.asList(3, 4, 5),
                Arrays.asList(6, 7, 8),

                Arrays.asList(0, 3, 6),
                Arrays.asList(1, 4, 7),
                Arrays.asList(2, 5, 8),

                Arrays.asList(0, 4, 8),
                Arrays.asList(2, 4, 6)
        );

        for (List<Integer> line : possibleLines)
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

    private List<Piece> getPiecesInTheLine(List<Integer> line) {
        ArrayList<Piece> linePieces = new ArrayList<>(line.size());

        for (Integer index : line)
            linePieces.add(pieces.get(index));

        return linePieces;
    }

    public boolean hasWon(Player possibleWinner) {
        List<Piece> winningLine = getWinningLine();

        return winningLine != null &&
               winningLine.get(0).isOwnedBy(possibleWinner);
    }

    public GameState put(Piece p, Location l) {
        final ArrayList<Piece> newPieces = new ArrayList<>(this.pieces);
        newPieces.set(getIndex(l), p);
        return new GameState(newPieces);
    }
}
