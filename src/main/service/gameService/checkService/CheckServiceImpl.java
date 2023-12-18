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
    public List<List<Move>> findPossMovesByColour(boolean isWhite) {
        List<Piece> pieces = findPiecesByColour(isWhite);
        List<List<Move>> moves = new ArrayList<>();

        for (Piece piece : pieces) {
            if (!boardManager.findPossMovesByPices(piece).isEmpty()) {
                moves.add(boardManager.findPossMovesByPices(piece));

            }
        }

        return moves;
    }

    @Override
    public int isKingInCheckByColour(boolean isWhite) {

        return findMovesCheckingKing(isWhite).size();
    }

    @Override
    public List<Move> findMovesCheckingKing(boolean isWhite) {
        List<Move> result = new ArrayList<>();
        King king = findKingByColour(isWhite);
        int kingRow = king.getCurrentRow(), kingCol = king.getCurrentCol();

        List<List<Move>> possMovesByOtherColour = findPossMovesByColour(!isWhite);
        for (List<Move> list : possMovesByOtherColour) {
            for (Move move : list) {
                if (move.getEndRow() == kingRow && kingCol == move.getEndCol()) {
                    result.add(move);
                }
            }
        }
        return result;
    }

    @Override
    public boolean isKingCheckMatebyColour(boolean isWhite) {
        King king = findKingByColour(isWhite);
        int picesCheckingTheKing = isKingInCheckByColour(isWhite);

        if (picesCheckingTheKing == 0) {
            return false;
        }
        List<Move> possMovesByKing = boardManager.findPossMovesByPices(king);

        List<List<Move>> possMovesByOtherColour = findPossMovesByColour(!isWhite);

        Set<Move> kingNotAllowed = new HashSet<>();

        for (Move move : possMovesByKing) {
            for (List<Move> list : possMovesByOtherColour) {

                for (Move move2 : list) {
                    if (move.getEndRow() == move2.getEndRow() && move.getEndCol() == move2.getEndCol()) {
                        kingNotAllowed.add(move);
                    }
                }
            }
        }

        if (possMovesByKing.size() == kingNotAllowed.size()) {
            if (picesCheckingTheKing == 2) {
                return true;
            }
            return !boardManager.canCheckBeBlockedByColour(isWhite);

        }
        return false;
    }

    @Override
    public List<int[]> findCheckPath(Move chekMove) {
        int startRow = chekMove.getStartRow();
        int startCol = chekMove.getStartCol();
        int endRow = chekMove.getEndRow();
        int endCol = chekMove.getEndCol();

        List<int[]> squaresToBlock = new ArrayList<>();

        // Calculate the direction of the move
        int rowDirection = Integer.compare(endRow, startRow);
        int colDirection = Integer.compare(endCol, startCol);

        // Calculate the squares along the path
        int currentRow = startRow + rowDirection;
        int currentCol = startCol + colDirection;

        while (currentRow != endRow || currentCol != endCol) {
            squaresToBlock.add(new int[] { currentRow, currentCol });
            currentRow += rowDirection;
            currentCol += colDirection;
        }

        return squaresToBlock;
    }

    @Override
    public boolean isDraw() {
        // if only king are left
        List<Piece> piecesWhite = findPiecesByColour(true);
        List<Piece> piecesBlack = findPiecesByColour(false);

        if (piecesBlack.size() == 1 && piecesWhite.size() == 1) {
            return true;
        }
        // setelmate logic
        King kingWhite = findKingByColour(true);
        King kingBlack = findKingByColour(false);

        List<Move> posMoveByWhite = findPossMovesByColour(true).stream()
                .flatMap(List::stream) 
                .collect(Collectors.toList());
       
                List<Move> posMoveByBlack = findPossMovesByColour(false).stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        int possKingWhiteMove = posMoveByWhite.size();
        int possKingBlackMove = posMoveByBlack.size();

        for (Move move : posMoveByWhite) {
            if (!boardManager.isLegalMove(kingWhite, move.getEndRow(), move.getEndCol())) {
                possKingWhiteMove--;
            }
        }

        for (Move move : posMoveByBlack) {
            if (!boardManager.isLegalMove(kingBlack, move.getEndRow(), move.getEndCol())) {
                possKingBlackMove--;
            }
        }

        if ((isKingInCheckByColour(true) == 0 && possKingWhiteMove == 0)
                || (isKingInCheckByColour(false) == 0 && possKingBlackMove == 0)) {
            return true;
        }
        return false;
    }

}
