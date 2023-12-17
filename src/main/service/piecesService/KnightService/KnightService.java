package src.main.service.piecesService.KnightService;

import src.main.entity.pieces.Knight;

public interface KnightService {

    Knight createKnight(boolean isWhite, int initialRow, int initialCol);

    boolean moveKnight(Knight knight, int newRow, int newCol);

    boolean isValidMove(Knight knight, int newRow, int newCol);

}
