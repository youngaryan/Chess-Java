package src.main.service.piecesService.queenService;

import src.main.entity.pieces.Queen;
import src.main.service.piecesService.bishopService.BishopService;
import src.main.service.piecesService.rookService.RookService;

public class QueenServiceImpl implements QueenService {
    private BishopService bishopService;
    private RookService rookService;

    public QueenServiceImpl(BishopService bishopService, RookService rookService) {
        this.bishopService = bishopService;
        this.rookService = rookService;
    }

    @Override
    public Queen createQueen(boolean isWhite, int initialRow, int initialCol) {
        Queen newQueen = new Queen(isWhite, initialRow, initialCol);
        return newQueen;
    }

    @Override
    public boolean moveQueen(Queen queen, int newRow, int newCol) {
        if (bishopService.moveBishop(queen, newRow, newCol)
                || rookService.moveRook(queen, newRow, newCol)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidMove(Queen queen, int newRow, int newCol) {
        if (bishopService.isValidMove(queen, newRow, newCol) || rookService.isValidMove(queen, newRow, newCol)) {
            return true;
        }
        return false;
    }

}
