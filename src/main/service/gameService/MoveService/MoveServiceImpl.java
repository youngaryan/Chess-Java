package src.main.service.gameService.MoveService;

import java.util.ArrayList;
import java.util.List;

import src.main.entity.game.Move;
import src.main.stat.statVar.StatField;

public class MoveServiceImpl implements MoveService {
    private static List<Move> allMoves;

    public MoveServiceImpl() {
        allMoves = new ArrayList<>();
    }

    @Override
    public List<Move> findAllMoves() {
        return allMoves;
    }

    @Override
    public List<Move> findAllMovesPlayerWhite() {
        List<Move> playedByWhite = new ArrayList<>();
        List<Move> allMovesList = findAllMoves();

        for (Move move : allMovesList) {
            if (move.getPlayer() == StatField.PLAYER_WHITE) {
                playedByWhite.add(move);
            }
        }
        return playedByWhite;
    }

    @Override
    public List<Move> findAllMovesPlayerBlack() {
        List<Move> playedByBlack = new ArrayList<>();
        List<Move> allMovesList = findAllMoves();

        for (Move move : allMovesList) {
            if (move.getPlayer() == StatField.PLAYER_BLACK) {
                playedByBlack.add(move);
            }
        }
        return playedByBlack;
    }

    @Override
    public Move findLastMove() {
        List<Move> allMovesList = findAllMoves();

        if (allMovesList.isEmpty()) {
            return null;
        }
        Move lastMove = allMovesList.get(allMovesList.size() - 1);
        return lastMove;
    }

    @Override
    public boolean addMove(Move move) {
        if (allMoves.add(move)) {
            move.setMoveNumber(allMoves.size());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteLastMove() {
        List<Move> allMovesList = findAllMoves();
        if (!allMovesList.isEmpty()) {
            allMovesList.remove(allMovesList.size() - 1);
            return true;
        }
        return false;
    }

}
