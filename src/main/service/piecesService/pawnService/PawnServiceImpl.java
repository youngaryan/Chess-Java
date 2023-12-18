package src.main.service.piecesService.pawnService;

import src.main.BoardManager;
import src.main.entity.game.Move;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Piece;
import src.main.stat.statMethod.InputChecker;
import src.main.stat.statVar.StatField;

public class PawnServiceImpl implements PawnService {
    private BoardManager boardManager;

    public PawnServiceImpl(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    public Pawn createPawn(boolean isWhite, int initialRow, int initialCol) {
        Pawn newPawn = new Pawn(isWhite, initialRow, initialCol, false);

        return newPawn;
    }


    @Override
    public Piece promotePawn(Pawn pawn, int newRow, int newCol, Class<? extends Piece> newPiece) {
        if (!isValidMove(pawn, newRow, newCol)) {
            return null;
        }
        boardManager.promotePice(pawn, newPiece, newRow, newCol);
        Piece[][] boerd = boardManager.getChessBoard();
        return boerd[newRow][newCol];

    }

    @Override
    public boolean isValidMove(Pawn pawn, int newRow, int newCol) {
        // getting the board

        Piece[][] boerd = boardManager.getChessBoard();
        if (!InputChecker.newGriddimentionChecker(newRow, newCol)
                || InputChecker.SameColourChecker(pawn, boerd[newRow][newCol])) {
            return false;
        }

        // curent position of the pawn
        int curentRow = pawn.getCurrentRow();
        int currentCol = pawn.getCurrentCol();

        // white pawn
        if (pawn.isWhite()) {
            boerd = boardManager.getChessBoard();
            // check for pawn which wants to move 2
            if (newRow - curentRow == 2) {
                // if it's not on the first row it can't move 2 steps
                if (pawn.isHasMoved()) {
                    return false;
                }
                // not posible to capture (change column) while pawn moves 2 steps
                if (newCol != currentCol) {
                    return false;
                }
                // check if no pieces on the way
                if (boerd[curentRow + 1][currentCol] != null || boerd[curentRow + 2][currentCol] != null) {
                    return false;
                }
                return true;
            }

            // check for simply moveing one step forward && check for catpuring
            if (newRow - curentRow == 1) {
                // check for moving forward
                if (newCol == currentCol) {
                    // check if no piece on the way
                    if (boerd[curentRow + 1][currentCol] != null) {
                        return false;
                    }
                    return true;
                }
                // check for capturing
                if (Math.abs(newCol - currentCol) == 1 && boerd[newRow][newCol] != null) {
                    return true;
                }
                if (curentRow == StatField.FIFTH) {
                    Move lastMove = boardManager.findTheLastMove();
                    if (lastMove.getPiece() instanceof Pawn && !lastMove.getPiece().isWhite()
                            && lastMove.getEndRow() == StatField.FIFTH && lastMove.getEndCol() == newCol
                            && lastMove.getStartRow() == StatField.SEVENTH) {
                        pawn.setAbleToEnPassant(true);
                        return true;
                    }
                }
            }

            return false;

        }
        // black pawn
        else {
            boerd = boardManager.getChessBoard();

            // check for pawn which wants to move 2
            if (curentRow - newRow == 2) {
                // if it's not on the first row it can't move 2 steps
                if (pawn.isHasMoved()) {
                    return false;
                }
                // not posible to capture (change column) while pawn moves 2 steps
                if (newCol != currentCol) {
                    return false;
                }
                // check if no pieces on the way
                if (boerd[curentRow - 1][currentCol] != null || boerd[curentRow - 2][currentCol] != null) {
                    return false;
                }
                return true;
            }

            // check for simply moveing one step forward && check for catpuring
            if (curentRow - newRow == 1) {
                // check for moving forward
                if (newCol == currentCol) {
                    // check if no piece on the way
                    if (boerd[curentRow - 1][currentCol] != null) {
                        return false;
                    }
                    return true;
                }
                // check for capturing
                if (Math.abs(newCol - currentCol) == 1 && boerd[newRow][newCol] != null) {
                    return true;
                }
                if (curentRow == StatField.FOURTH) {
                    Move lastMove = boardManager.findTheLastMove();
                    if (lastMove.getPiece() instanceof Pawn && lastMove.getPiece().isWhite()
                            && lastMove.getEndRow() == StatField.FOURTH && lastMove.getEndCol() == newCol
                            && lastMove.getStartRow() == StatField.SECOND) {
                        pawn.setAbleToEnPassant(true);
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
