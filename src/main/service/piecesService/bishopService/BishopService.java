package src.main.service.piecesService.bishopService;

import src.main.entity.pieces.Bishop;
import src.main.entity.pieces.Piece;

public interface BishopService {
    Bishop createBishop(boolean isWhite, int initialRow, int initialCol);

    boolean moveBishop(Piece bishop, int newRow, int newCol);

    boolean isValidMove(Piece bishop, int newRow, int newCol);
}
