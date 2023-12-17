package src.main.service.gameService.checkService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import src.main.BoardManager;
import src.main.entity.game.Move;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Piece;

public class CheckServiceImpl implements CheckService {
    private BoardManager boardManager;

    public CheckServiceImpl(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    @Override
    public King findKingByColour(boolean isWhite) {
        List<Piece> pieces = findPiecesByColour(isWhite);

        for (Piece piece : pieces) {
            if (piece.getClass().getSimpleName().equals("King")) {
                return (King) piece;
            }
        }
        return null;

    }

    @Override
    public List<Piece> findPiecesByColour(boolean isWhite) {
        List<Piece> pieces = new ArrayList<>();
        Piece piece;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                piece = boardManager.getPiece(i, j);
                if (piece != null && piece.isWhite() == isWhite) {
                    pieces.add(piece);
                }
            }
        }
        return pieces;
    }

    @Override
    public List<Move> findPossMovesByColour(boolean isWhite) {
        List<Piece> pieces = findPiecesByColour(isWhite);
        List<List<Move>> moves = new ArrayList<>();

        for (Piece piece : pieces) {
            moves.add(boardManager.findPossMovesByPices(piece));
        }

        return moves.stream()
                .flatMap(List::stream) // flattening using flatMap
                .collect(Collectors.toList());
    }

    @Override
    public boolean isKingInCheckByColour(boolean isWhite) {
        King king = findKingByColour(isWhite);
        int kingRow = king.getCurrentRow(), kingCol = king.getCurrentCol();

        List<Move> possMovesByOtherColour = findPossMovesByColour(!isWhite);

        for (Move move : possMovesByOtherColour) {
            if (move.getEndRow() == kingRow && kingCol == move.getEndCol()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isKingCheckMatebyColour(boolean isWhite) {
        King king = findKingByColour(isWhite);

        if (!isKingInCheckByColour(isWhite)) {
            return false;
        }
        List<Move> possMovesByKing = boardManager.findPossMovesByPices(king);

        List<Move> possMovesByOtherColour = findPossMovesByColour(!isWhite);

        Set<Move> kingNotAllowed = new HashSet<>();

        for (Move move : possMovesByKing) {
            for (Move move2 : possMovesByOtherColour) {
                if (move.getEndRow() == move2.getEndRow() && move.getEndCol() == move2.getEndCol()) {
                    kingNotAllowed.add(move);
                }
            }
        }

        return possMovesByKing.size() == kingNotAllowed.size();
    }

}
