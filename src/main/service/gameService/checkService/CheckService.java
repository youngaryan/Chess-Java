package src.main.service.gameService.checkService;

import java.util.List;

import src.main.entity.game.Move;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Piece;

public interface CheckService {

    King findKingByColour(boolean isWhite);

    List<Piece> findPiecesByColour(boolean isWhite);

    List<List<Move>> findPossMovesByColour(boolean isWhite);

    int isKingInCheckByColour(boolean isWhite);

    boolean isKingCheckMatebyColour(boolean isWhite);

    List<Move> findMovesCheckingKing(boolean isWhite);

    List<int[]> findCheckPath(Move chekMove);

}
