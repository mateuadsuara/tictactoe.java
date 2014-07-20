package com.github.demonh3x.tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {
    private final List<Piece> pieces;

    public GameState(List<Piece> pieces) {
        this.pieces = pieces;
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
            if (isAWinningLine(line))
                return true;

        return false;
    }

    private boolean isAWinningLine(List<Integer> line) {
        List<Piece> piecesInTheLine = getPiecesInTheLine(line);
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
}
