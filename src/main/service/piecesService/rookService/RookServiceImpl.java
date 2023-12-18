package src.main.service.piecesService.rookService;

import src.main.BoardManager;
import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Rook;
import src.main.stat.statMethod.InputChecker;

public class RookServiceImpl implements RookService {
    private BoardManager boardManager;

    public RookServiceImpl(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    public Rook createRook(boolean isWhite, int initialRow, int initialCol) {
        Rook newRook = new Rook(isWhite, initialRow, initialCol, false);
        return newRook;
    }

    @Override
    public boolean isValidMove(Piece rook, int newRow, int newCol) {
        // the grid checker
        if (!InputChecker.newGriddimentionChecker(newRow, newCol)) {
            return false;
        }
        // getting the board

        Piece[][] boerd = boardManager.getChessBoard();

        // curent position of the pawn
        int curentRow = rook.getCurrentRow();
        int currentCol = rook.getCurrentCol();

        // not a rook move
        if (InputChecker.SameColourChecker(boerd[newRow][newCol], rook)
                || (curentRow != newRow && currentCol != newCol)) {
            return false;
        }

        // the same row (horizantal moves)
        if (curentRow == newRow) {
            if (newCol > currentCol) {
                for (int i = newCol - 1; i > currentCol; i--) {
                    // if the same piece on the way illigal move
                    if (boerd[curentRow][i] != null) {
                        return false;
                    }

                }

            } else {
                for (int i = newCol + 1; i < currentCol; i++) {
                    // if the same piece on the way illigal move
                    if (boerd[curentRow][i] != null) {
                        return false;
                    }
                }
            }

        } // the same column (vertical moves)
        else {

            if (newRow > curentRow) {
                // if the same piece on the way illigal move
                for (int i = newRow - 1; i > curentRow; i--) {
                    if (boerd[i][currentCol] != null) {
                        return false;
                    }
                }
            } else {
                for (int i = newRow + 1; i < curentRow; i++) {
                    // if the same piece on the way illigal move
                    if (boerd[i][currentCol] != null) {
                        return false;
                    }
                }
            }

        }

        return true;
    }

}
