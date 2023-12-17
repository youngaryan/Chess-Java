package src.main.service.piecesService.kingService;

import src.main.BoardManager;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Rook;
import src.main.service.piecesService.rookService.RookService;
import src.main.service.piecesService.rookService.RookServiceImpl;
import src.main.stat.statMethod.InputChecker;

public class KingServiceImpl implements KingService {
    private BoardManager boardManager;

    public KingServiceImpl(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    public King createKing(boolean isWhite, int initialRow, int initialCol) {
        King newKing = new King(isWhite, initialRow, initialCol, false);
        return newKing;
    }

    @Override
    public boolean moveKing(King king, int newRow, int newCol) {
        if (!isValidMove(king, newRow, newCol)) {
            return false;
        }

        if (newCol > king.getCurrentCol() && Math.abs(king.getCurrentCol() - newCol) == 2) {
            Rook rook = boardManager.getPiece(king.getCurrentRow(), newCol + 1);

            RookService rookService = new RookServiceImpl(boardManager);
            rookService.moveRook(rook, king.getCurrentRow(), newCol - 1);
        } else if (newCol < king.getCurrentCol() && Math.abs(king.getCurrentCol() - newCol) == 2) {
            Rook rook = boardManager.getPiece(newRow, newCol - 2);

            RookService rookService = new RookServiceImpl(boardManager);
            rookService.moveRook(rook, newRow, newCol + 1);
        }
        boardManager.movePiece(king, newRow, newCol);
        king.setHasMoved(true);
        return true;
    }

    @Override
    public boolean isValidMove(King king, int newRow, int newCol) {
        Piece[][] boerd = boardManager.getChessBoard();

        if (!InputChecker.newGriddimentionChecker(newRow, newCol)
                || InputChecker.SameColourChecker(king, boerd[newRow][newCol])) {
            return false;
        }

        // getting the board

        // curent position of the pawn
        int curentRow = king.getCurrentRow();
        int currentCol = king.getCurrentCol();

        if (Math.abs(curentRow - newRow) < 2 && Math.abs(currentCol - newCol) < 2) {

            return true;
        }
        if (curentRow == newRow && Math.abs(currentCol - newCol) == 2 && !king.isHasMoved()) {

            if (boerd[newRow][newCol] == null) {

                if (newCol > currentCol) {

                    if (boerd[newRow][newCol - 1] == null && boerd[newRow][newCol + 1] != null
                            && Rook.class.isInstance(boardManager.getPiece(curentRow, newCol + 1))) {

                        Rook rook = boardManager.getPiece(curentRow, newCol + 1);
                        if (!rook.isHasMoved()) {
                            return true;
                        } else {
                            return false;
                        }

                    }
                    return false;
                } else {

                    if (boerd[newRow][newCol + 1] == null && boerd[newRow][newCol - 2] != null
                            && Rook.class.isInstance(boardManager.getPiece(curentRow, newCol - 2))) {

                        Rook rook = boardManager.getPiece(curentRow, newCol - 2);
                        if (!rook.isHasMoved()) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                    return false;
                }
            }
        }
        return false;
    }

}
