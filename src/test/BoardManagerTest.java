package src.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.game.Move;
import src.main.entity.pieces.Bishop;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Knight;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Queen;
import src.main.entity.pieces.Rook;

import src.main.stat.statVar.StatField;

public class BoardManagerTest {
    private BoardManager boardManager;
    private Piece[][] board;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
        board = boardManager.getChessBoard();
    }

    @Test
    public void testBoardManagerNotNull() {
        assertNotNull(boardManager);
    }

    @Test
    public void testGetPiece() {
        assertNotNull(boardManager.getPiece(StatField.FIRST, StatField.FIRST));
    }

    @Test
    public void centerNull() {
        assertTrue(board[StatField.FOURTH][StatField.FOURTH] == null);
    }

    @Test
    public void pawnWhiteCreated1() {
        boolean isPawn = true;
        // no pawn in first row
        for (int i = 0; i < 8; i++) {
            if (!board[StatField.SECOND][i].getClass().getSimpleName().equals("Pawn")
                    || board[StatField.SECOND][i].isWhite() != true) {
                isPawn = false;
                break;
            }
        }
        assertTrue(isPawn);
    }

    @Test
    public void pawnBlackCreated1() {
        boolean isPawn = true;
        for (int i = 0; i < 8; i++) {
            if (!board[StatField.SEVENTH][i].getClass().getSimpleName().equals("Pawn")
                    || board[StatField.SEVENTH][i].isWhite() == true) {
                isPawn = false;
                break;
            }
        }
        assertTrue(isPawn);
    }

    @Test
    public void rookWhiteCreated() {

        assertTrue(board[StatField.FIRST][StatField.FIRST].getClass().getSimpleName().equals("Rook")
                && board[StatField.FIRST][StatField.FIRST].isWhite() == true);
    }

    @Test
    public void rookWhiteCreated1() {
        assertTrue(board[StatField.FIRST][StatField.EIGHTS].getClass().getSimpleName().equals("Rook")
                && board[StatField.FIRST][StatField.EIGHTS].isWhite() == true);
    }

    @Test
    public void rookBlackCreated() {
        assertTrue(board[StatField.EIGHTS][StatField.FIRST].getClass().getSimpleName().equals("Rook")
                && board[StatField.EIGHTS][StatField.FIRST].isWhite() != true);
    }

    @Test
    public void rookBlackCreated1() {
        assertTrue(board[StatField.EIGHTS][StatField.EIGHTS].getClass().getSimpleName().equals("Rook")
                && board[StatField.EIGHTS][StatField.EIGHTS].isWhite() != true);
    }

    @Test
    public void kingWhiteCreated() {
        assertTrue(board[StatField.FIRST][StatField.FIFTH].getClass().getSimpleName().equals("King")
                && board[StatField.FIRST][StatField.FIFTH].isWhite() == true);
    }

    @Test
    public void kingBlackCreated() {
        assertTrue(board[StatField.EIGHTS][StatField.FIFTH].getClass().getSimpleName().equals("King")
                && board[StatField.EIGHTS][StatField.FIFTH].isWhite() != true);
    }

    @Test
    public void bishopWhiteCreated() {
        assertTrue(board[StatField.FIRST][StatField.THIRD].getClass().getSimpleName().equals("Bishop")
                && board[StatField.FIRST][StatField.THIRD].isWhite() == true);
    }

    @Test
    public void bishopWhiteCreated1() {
        assertTrue(board[StatField.FIRST][StatField.SIXTH].getClass().getSimpleName().equals("Bishop")
                && board[StatField.FIRST][StatField.SIXTH].isWhite() == true);
    }

    @Test
    public void bishopBlackCreated() {

        assertTrue(board[StatField.EIGHTS][StatField.THIRD].getClass().getSimpleName().equals("Bishop")
                && board[StatField.EIGHTS][StatField.THIRD].isWhite() != true);
    }

    @Test
    public void bishopBlackCreated1() {
        assertTrue(board[StatField.EIGHTS][StatField.SIXTH].getClass().getSimpleName().equals("Bishop")
                && board[StatField.EIGHTS][StatField.SIXTH].isWhite() != true);
    }

    @Test
    public void queenWhiteCreated() {

        assertTrue(board[StatField.FIRST][StatField.FOURTH].getClass().getSimpleName().equals("Queen")
                && board[StatField.FIRST][StatField.FOURTH].isWhite() == true);
    }

    @Test
    public void queenBlackCreated() {
        assertTrue(board[StatField.EIGHTS][StatField.FOURTH].getClass().getSimpleName().equals("Queen")
                && board[StatField.EIGHTS][StatField.FOURTH].isWhite() != true);
    }

    @Test
    public void knightWhiteCreated() {
        assertTrue(board[StatField.FIRST][StatField.SECOND].getClass().getSimpleName().equals("Knight")
                && board[StatField.FIRST][StatField.SECOND].isWhite() == true);
    }

    @Test
    public void knightWhiteCreated1() {
        assertTrue(board[StatField.FIRST][StatField.SEVENTH].getClass().getSimpleName().equals("Knight")
                && board[StatField.FIRST][StatField.SEVENTH].isWhite() == true);
    }

    @Test
    public void knightBlackCreated() {
        assertTrue(board[StatField.EIGHTS][StatField.SECOND].getClass().getSimpleName().equals("Knight")
                && board[StatField.EIGHTS][StatField.SECOND].isWhite() != true);
    }

    @Test
    public void knightBlackCreated1() {
        assertTrue(board[StatField.EIGHTS][StatField.SEVENTH].getClass().getSimpleName().equals("Knight")
                && board[StatField.EIGHTS][StatField.SEVENTH].isWhite() != true);
    }

    @Test
    public void movePieceTest() {
        boardManager.movePiece(boardManager.getPiece(StatField.SECOND, StatField.SECOND), StatField.THIRD,
                StatField.SECOND);
        assertTrue(board[StatField.THIRD][StatField.SECOND].getClass().getSimpleName().equals("Pawn")
                && board[StatField.THIRD][StatField.SECOND].isWhite() == true
                && board[StatField.SECOND][StatField.SECOND] == null);
    }

    @Test
    public void findPossMovesByPicesTest() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        List<Move> moves = boardManager.findPossMovesByPices(pawn);

        assertTrue(moves.size() == 2);
    }

    @Test
    public void findPossMovesByPicesTest1() {
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        List<Move> moves = boardManager.findPossMovesByPices(knight);

        assertTrue(moves.size() == 2);
    }

    @Test
    public void findPossMovesByPicesTest2() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FIRST);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(knight);

        assertTrue(moves.size() == 1);
    }

    @Test
    public void findPossMovesByPicesTest3() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FIRST);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(rook);

        assertTrue(moves.size() == 1);
    }

    @Test
    public void findPossMovesByPicesTest4() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Pawn pawn2 = boardManager.getPiece(StatField.SECOND, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FIRST);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(pawn2, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(knight);

        assertTrue(moves.size() == 0);
    }

    @Test
    public void findPossMovesByPicesTest5() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Pawn pawn2 = boardManager.getPiece(StatField.SECOND, StatField.THIRD);
        Pawn pawn3 = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FIRST);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(pawn2, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(pawn2, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(pawn3, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(knight);

        assertTrue(moves.size() == 1);
    }

    @Test
    public void findPossMovesByPicesTest6() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(bishop);

        assertTrue(moves.size() == 5);
    }

    @Test
    public void findPossMovesByPicesTest7() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Pawn pawn2 = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(pawn2, StatField.THIRD, StatField.SECOND);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(bishop);

        assertTrue(moves.size() == 7);
    }

    @Test
    public void findPossMovesByPicesTest8() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(queen);

        assertTrue(moves.size() == 1);
    }

    @Test
    public void findPossMovesByPicesTest9() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        List<Move> moves = boardManager.findPossMovesByPices(queen);

        assertTrue(moves.size() == 8);
    }

    @Test
    public void findPossMovesByPicesTest10() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Pawn pawn2 = boardManager.getPiece(StatField.SEVENTH, StatField.SECOND);

        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.FIFTH, StatField.SEVENTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
        boardManager.makeMove(pawn1, StatField.THIRD, StatField.FIRST);

        boardManager.makeMove(knight, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(pawn2, StatField.SIXTH, StatField.SECOND);

        List<Move> moves = boardManager.findPossMovesByPices(king);

        assertTrue(moves.size() == 3);
    }

    @Test
    public void findPossMovesByPicesTest11() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Pawn pawn2 = boardManager.getPiece(StatField.SEVENTH, StatField.SECOND);
        Pawn pawn3 = boardManager.getPiece(StatField.SECOND, StatField.SEVENTH);

        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Bishop bishop1 = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);

        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Knight knight2 = boardManager.getPiece(StatField.FIRST, StatField.SEVENTH);

        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.FIFTH, StatField.SEVENTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
        boardManager.makeMove(pawn1, StatField.THIRD, StatField.FIRST);

        boardManager.makeMove(knight, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(pawn2, StatField.SIXTH, StatField.SECOND);

        boardManager.makeMove(pawn3, StatField.THIRD, StatField.SEVENTH);
        boardManager.makeMove(pawn2, StatField.FIFTH, StatField.SECOND);

        boardManager.makeMove(bishop1, StatField.SECOND, StatField.SEVENTH);
        boardManager.makeMove(pawn2, StatField.FOURTH, StatField.SECOND);

        boardManager.makeMove(knight2, StatField.THIRD, StatField.SIXTH);
        boardManager.makeMove(pawn2, StatField.THIRD, StatField.SECOND);

        List<Move> moves = boardManager.findPossMovesByPices(king);

        assertTrue(moves.size() == 5);
    }

    @Test
    public void isKingCheckedByClolour() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.FIFTH, StatField.SEVENTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        int check = boardManager.isKingInCheckByColour(false);
        assertTrue(check == 0);

    }

    @Test
    public void isKingCheckedByClolour1() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.FIFTH, StatField.SEVENTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SEVENTH, StatField.FIFTH);

        int check = boardManager.isKingInCheckByColour(false);
        assertTrue(check == 1);

    }

    @Test
    public void isKingCheckMatedByClolour() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SIXTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        boardManager.makeMove(pawn, StatField.THIRD, StatField.SIXTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIFTH);

        boolean check = boardManager.isKingCheckMatebyColour(true);

        assertFalse(check);
    }

    @Test
    public void isKingCheckMatedByClolour1() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SIXTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        Pawn pawn3 = boardManager.getPiece(StatField.SECOND, StatField.SEVENTH);

        Queen queen = boardManager.getPiece(StatField.EIGHTS, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.SIXTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIFTH);

        boardManager.makeMove(pawn3, StatField.FOURTH, StatField.SEVENTH);
        boardManager.makeMove(queen, StatField.FOURTH, StatField.EIGHTS);

        boolean check = boardManager.isKingCheckMatebyColour(true);

        assertTrue(check);
    }

    @Test
    public void isKingCheckMatedByClolour2() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.FIFTH, StatField.SEVENTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        boardManager.makeMove(queen, StatField.SEVENTH, StatField.FIFTH);

        boolean check = boardManager.isKingCheckMatebyColour(false);
        assertFalse(check);

    }

    @Test
    public void pinMoveTest() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        Pawn pawnBlack = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        Pawn pawnBlackPined = boardManager.getPiece(StatField.SEVENTH, StatField.FOURTH);

        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FIFTH);
        boardManager.makeMove(pawnBlack, StatField.SIXTH, StatField.FIFTH);

        boardManager.makeMove(bishop, StatField.FIFTH, StatField.SECOND);
        boardManager.makeMove(pawnBlackPined, StatField.SIXTH, StatField.FOURTH);

        board = boardManager.getChessBoard();

        assertTrue(board[StatField.SIXTH][StatField.FOURTH] == null
                && board[StatField.SEVENTH][StatField.FOURTH] instanceof Pawn);

    }

    @Test
    public void kingNextToEachOther() {

        Piece[][] board = boardManager.getChessBoard();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
            }
        }
        board[StatField.FIRST][StatField.THIRD] = new King(true, StatField.FIRST, StatField.THIRD, true);
        board[StatField.FIRST][StatField.FIRST] = new King(false, StatField.FIRST, StatField.FIRST, true);

        assertFalse(boardManager.isLegalMove(boardManager.getPiece(StatField.FIRST, StatField.THIRD), StatField.FIRST,
                StatField.SECOND));
    }
}
