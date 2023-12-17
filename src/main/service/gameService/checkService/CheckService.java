package src.main.service.gameService.checkService;

import java.util.List;

import src.main.entity.game.Move;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Piece;

public interface CheckService {

    King findKingByColour(boolean isWhite);

    List<Piece> findPiecesByColour(boolean isWhite);

    List<Move> findPossMovesByColour(boolean isWhite);

    boolean isKingInCheckByColour(boolean isWhite);

    boolean isKingCheckMatebyColour(boolean isWhite);

}
