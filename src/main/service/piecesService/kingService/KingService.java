package src.main.service.piecesService.kingService;

import src.main.entity.pieces.King;

public interface KingService {

    King createKing(boolean isWhite, int initialRow, int initialCol);

    // add castel
    boolean moveKing(King king, int newRow, int newCol);

    boolean isValidMove(King king, int newRow, int newCol);
}