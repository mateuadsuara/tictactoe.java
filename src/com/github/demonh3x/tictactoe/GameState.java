package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameState {
    private final List<Piece> pieces;

    public static final int ROWS = 3;
    public static final int COLUMNS = 3;

    public GameState(List<Piece> pieces) {
        if (pieces.size() != 9)
            throw new IllegalArgumentException("A game state should have 9 pieces!");

        this.pieces = Collections.unmodifiableList(pieces);
    }

    public Piece lookAt(int x, int y){
        final int index = (x * ROWS) + y;

        return pieces.get(index);
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
}
