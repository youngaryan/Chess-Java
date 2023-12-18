package src.main.service.piecesService.KnightService;

import src.main.BoardManager;
import src.main.entity.pieces.Knight;
import src.main.entity.pieces.Piece;
import src.main.stat.statMethod.InputChecker;

public class KnightServiceImpl implements KnightService {
    private BoardManager boardManager;

    public KnightServiceImpl(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    public Knight createKnight(boolean isWhite, int initialRow, int initialCol) {
        Knight newKnight = new Knight(isWhite, initialRow, initialCol);
        return newKnight;
    }

    @Override
    public boolean isValidMove(Knight knight, int newRow, int newCol) {
        Piece[][] boerd = boardManager.getChessBoard();
        if (!InputChecker.newGriddimentionChecker(newRow, newCol)
                || InputChecker.SameColourChecker(knight, boerd[newRow][newCol])) {
            return false;
        }

        int row = knight.getCurrentRow();
        int col = knight.getCurrentCol();

        if ((Math.abs(row - newRow) == 2 && Math.abs(col - newCol) == 1)
                || (Math.abs(row - newRow) == 1 && Math.abs(col - newCol) == 2)) {
            return true;
        }
        return false;
    }

}
