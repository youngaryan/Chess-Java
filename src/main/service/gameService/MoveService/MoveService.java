package src.main.service.gameService.MoveService;

import java.util.List;

import src.main.entity.game.Move;
import src.main.entity.pieces.Piece;

public interface MoveService {

    List<Move> findAllMoves();

    List<Move> findAllMovesPlayerWhite();

    List<Move> findAllMovesPlayerBlack();

    Move findLastMove();

    boolean addMove(Piece piece, int endRow, int endCol, int player);

    boolean deleteLastMove();

}
