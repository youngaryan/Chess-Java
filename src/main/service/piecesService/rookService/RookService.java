package src.main.service.piecesService.rookService;

import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Rook;

public interface RookService {
    Rook createRook(boolean isWhite, int initialRow, int initialCol);

    boolean isValidMove(Piece rook, int newRow, int newCol);
}
