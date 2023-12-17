package src.main.service.piecesService.pawnService;

import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Piece;

public interface PawnService {
    Pawn createPawn(boolean isWhite, int initialRow, int initialCol);

    boolean movePawn(Pawn pawn, int newRow, int newCol);

    // add unpaswnt
    Piece promotePawn(Pawn pawn, int newRow, int newCol, Class<? extends Piece> newPiece);

    boolean isValidMove(Pawn pawn, int newRow, int newCol);

}
