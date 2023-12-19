package src.test.gameServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.game.Move;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Queen;
import src.main.service.gameService.checkService.CheckService;
import src.main.service.gameService.checkService.CheckServiceImpl;
import src.main.stat.statVar.StatField;

public class CheckServiceTest {
    private BoardManager boardManager;
    private CheckService checkService;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
        checkService = new CheckServiceImpl(boardManager);
    }

    @Test
    public void findPiecesByColourWhiteTest() {
        List<Piece> findPiecesByColour = checkService.findPiecesByColour(true);
        boolean check = true;

        for (Piece piece : findPiecesByColour) {
            if (!piece.isWhite()) {
                check = false;
                break;
            }
        }

        assertTrue(check && findPiecesByColour.size() == 16);
    }

    @Test
    public void findPiecesByColourBlackTest() {
        List<Piece> findPiecesByColour = checkService.findPiecesByColour(false);
        boolean check = true;

        for (Piece piece : findPiecesByColour) {
            if (piece.isWhite()) {
                check = false;
                break;
            }
        }

        assertTrue(check && findPiecesByColour.size() == 16);
    }

    @Test
    public void findWhiteKing() {
        King king = checkService.findKingByColour(true);

        assertTrue(
                king.isWhite() && king.getCurrentCol() == StatField.FIFTH && king.getCurrentRow() == StatField.FIRST);
    }

    @Test
    public void findBlackKing() {
        King king = checkService.findKingByColour(false);

        assertTrue(
                !king.isWhite() && king.getCurrentCol() == StatField.FIFTH && king.getCurrentRow() == StatField.EIGHTS);
    }

    @Test
    public void findMovesByColourWhite() {
        List<List<Move>> moves = checkService.findPossMovesByColour(true);

        assertTrue(moves.size() == 10);
    }

    @Test
    public void findMovesByColourVlack() {
        List<List<Move>> moves = checkService.findPossMovesByColour(false);

        assertTrue(moves.size() == 10);
    }

    @Test
    public void isKingInCheckByColourTest() {
        assertTrue(checkService.isKingInCheckByColour(false) == 0);
    }

    @Test
    public void isKingCheckMatebyColourTest() {
        assertFalse(checkService.isKingCheckMatebyColour(false));
    }

    @Test
    public void isDraw() {
        assertFalse(checkService.isDraw());
    }

    @Test
    public void isDraw1() {
        Piece[][] board = boardManager.getChessBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
        board[StatField.FIRST][StatField.FIRST] = new King(true, StatField.FIRST, StatField.FIRST, true);
        board[StatField.FIRST][StatField.FIFTH] = new King(false, StatField.FIRST, StatField.FIFTH, true);

        assertTrue(checkService.isDraw());
    }

    @Test
    public void isDraw2() {
        Piece[][] board = boardManager.getChessBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }

        board[StatField.FIRST][StatField.FIRST] = new King(true, StatField.FIRST, StatField.FIRST, true);
        board[StatField.FIRST][StatField.FIFTH] = new King(false, StatField.FIRST, StatField.FIFTH, true);

        board[StatField.SECOND][StatField.THIRD] = new Queen(false, StatField.SECOND, StatField.THIRD);

        assertTrue(checkService.isDraw());
    }
}