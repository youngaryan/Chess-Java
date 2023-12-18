package src.main.service.gameService.MoveService;

import java.util.List;

import src.main.entity.game.Move;

public interface MoveService {

    List<Move> findAllMoves();

    List<Move> findAllMovesPlayerWhite();

    List<Move> findAllMovesPlayerBlack();

    Move findLastMove();

    boolean addMove(Move move);

    boolean deleteLastMove();

}
