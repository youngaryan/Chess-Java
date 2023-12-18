package src.main.service.piecesService.KnightService;

import src.main.entity.pieces.Knight;

public interface KnightService {

    Knight createKnight(boolean isWhite, int initialRow, int initialCol);

    boolean isValidMove(Knight knight, int newRow, int newCol);

}
