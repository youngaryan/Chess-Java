package src.main.service.piecesService.bishopService;

import src.main.BoardManager;
import src.main.entity.pieces.Bishop;
import src.main.entity.pieces.Piece;
import src.main.stat.statMethod.InputChecker;

public class BishopServiceImpl implements BishopService {
    private BoardManager boardManager;

    public BishopServiceImpl(BoardManager boardManager) {
        this.boardManager = boardManager;
    }
    
    @Override
    public Bishop createBishop(boolean isWhite, int initialRow, int initialCol) {
        Bishop newBishop = new Bishop(isWhite, initialRow, initialCol);

        return newBishop;
    }

    @Override
    public boolean moveBishop(Piece bishop, int newRow, int newCol) {
        if (!isValidMove(bishop, newRow, newCol)) {
            return false;
        }
        boardManager.movePiece(bishop, newRow, newCol);
        return true;
    }

    @Override
    public boolean isValidMove(Piece bishop, int newRow, int newCol) {
        Piece[][] boerd = boardManager.getChessBoard();
        if (!InputChecker.newGriddimentionChecker(newRow, newCol)
                || InputChecker.SameColourChecker(bishop, boerd[newRow][newCol])) {
            return false;
        }

        int currentRow = bishop.getCurrentRow();
        int currentCol = bishop.getCurrentCol();

        // check if it is a possible bishop move
        // the diff between curr coulmn , new coulumn and curr row and new row should be
        // the same

        if (Math.abs(currentRow - newRow) != Math.abs(currentCol - newCol)) {
            return false;
        }

        int rowDirection = (newRow - currentRow) / Math.abs(newRow - currentRow);
        int colDirection = (newCol - currentCol) / Math.abs(newCol - currentCol);

        for (int i = 1; i < Math.abs(newRow - currentRow); i++) {
            int checkRow = currentRow + i * rowDirection;
            int checkCol = currentCol + i * colDirection;

            if (boardManager.getChessBoard()[checkRow][checkCol] != null) {
                // There is a piece in the way
                return false;
            }
        }

        return true;
    }

}
