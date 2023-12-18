package src.main.service.piecesService.queenService;

import src.main.entity.pieces.Queen;

public interface QueenService {
    Queen createQueen(boolean isWhite, int initialRow, int initialCol);

    boolean isValidMove(Queen queen, int newRow, int newCol);
}
