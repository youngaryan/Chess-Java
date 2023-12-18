package src.test.piecesServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.pieces.Bishop;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Knight;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Queen;
import src.main.entity.pieces.Rook;
import src.main.stat.statVar.StatField;

public class KingServiceTest {
    private BoardManager boardManager;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
    }

    @Test
    public void validMove() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.SECOND, StatField.FIFTH);
        assertTrue(check);
    }

    @Test
    public void validMove1() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.SECOND, StatField.FIFTH);
        boolean check1 = boardManager.makeMove(king, StatField.THIRD, StatField.FIFTH);
        assertTrue(check && check1);
    }

    @Test
    public void validMove2() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.SECOND, StatField.FIFTH);
        boolean check1 = boardManager.makeMove(king, StatField.THIRD, StatField.FOURTH);
        assertTrue(check && check1);
    }

    @Test
    public void validMove3() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.SECOND, StatField.FIFTH);
        boolean check1 = boardManager.makeMove(king, StatField.FIRST, StatField.FIFTH);
        assertTrue(check && check1);
    }

    // casteling test knigside white
    @Test
    public void validMove4() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SEVENTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SECOND, StatField.FIFTH);

        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.SEVENTH);
        assertTrue(check);

    }

    @Test
    public void validMove5() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SEVENTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SECOND, StatField.FIFTH);

        boardManager.makeMove(king, StatField.FIRST, StatField.SEVENTH);

        Piece[][] board = boardManager.getChessBoard();

        assertTrue(board[StatField.FIRST][StatField.SIXTH] instanceof Rook
                && board[StatField.FIRST][StatField.EIGHTS] == null);

    }

    // casteling test knigside black
    @Test
    public void validMove6() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SEVENTH);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SEVENTH, StatField.FIFTH);

        boolean check = boardManager.makeMove(king, StatField.EIGHTS, StatField.SEVENTH);
        assertTrue(check);

    }

    @Test
    public void validMove7() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SEVENTH);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SEVENTH, StatField.FIFTH);

        boardManager.makeMove(king, StatField.EIGHTS, StatField.SEVENTH);
        Piece[][] board = boardManager.getChessBoard();

        assertTrue(board[StatField.EIGHTS][StatField.SIXTH] instanceof Rook
                && board[StatField.EIGHTS][StatField.EIGHTS] == null);
    }

    // casteling test queen white
    @Test
    public void validMove8() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.THIRD);

        assertTrue(check);

    }

    @Test
    public void validMove9() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(king, StatField.FIRST, StatField.THIRD);
        Piece[][] board = boardManager.getChessBoard();

        assertTrue(board[StatField.FIRST][StatField.FOURTH] instanceof Rook
                && board[StatField.FIRST][StatField.FIRST] == null);
    }

    // casteling test queen black
    @Test
    public void validMove10() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.EIGHTS, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.SIXTH, StatField.THIRD);
        boardManager.makeMove(queen, StatField.SEVENTH, StatField.FOURTH);

        boolean check = boardManager.makeMove(king, StatField.EIGHTS, StatField.THIRD);
        assertTrue(check);

    }

    @Test
    public void validMove11() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.EIGHTS, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.SIXTH, StatField.THIRD);
        boardManager.makeMove(queen, StatField.SEVENTH, StatField.FOURTH);

        boardManager.makeMove(king, StatField.EIGHTS, StatField.THIRD);

        Piece[][] board = boardManager.getChessBoard();

        assertTrue(board[StatField.EIGHTS][StatField.FOURTH] instanceof Rook
                && board[StatField.EIGHTS][StatField.FIRST] == null);

    }

    @Test
    public void invalidMove() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.SEVENTH);
        assertFalse(check);
    }

    @Test
    public void invalidMove1() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.SECOND);
        assertFalse(check);
    }

    @Test
    public void invalidMove2() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.SECOND, StatField.FIFTH);
        assertFalse(check);
    }

    // casteling test knigside white
    @Test
    public void invalidMove3() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.SEVENTH);
        assertFalse(check);
    }

    @Test
    public void invalidMove4() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SECOND, StatField.EIGHTS);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SEVENTH);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.EIGHTS);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SECOND, StatField.FIFTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.EIGHTS);

        boardManager.makeMove(rook, StatField.SECOND, StatField.EIGHTS);
        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.SEVENTH);
        assertFalse(check);

    }

    @Test
    public void invalidMove5() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SECOND, StatField.EIGHTS);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SEVENTH);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.EIGHTS);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SECOND, StatField.FIFTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.EIGHTS);

        boardManager.makeMove(rook, StatField.SECOND, StatField.EIGHTS);
        boardManager.makeMove(rook, StatField.FIRST, StatField.EIGHTS);

        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.SEVENTH);
        assertFalse(check);

    }

    // casteling test knigside black
    @Test
    public void invalidMove6() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.EIGHTS);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SEVENTH);
        Rook rook = boardManager.getPiece(StatField.EIGHTS, StatField.EIGHTS);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SEVENTH, StatField.FIFTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.EIGHTS);

        boardManager.makeMove(rook, StatField.SEVENTH, StatField.EIGHTS);
        boolean check = boardManager.makeMove(king, StatField.EIGHTS, StatField.SEVENTH);
        assertFalse(check);

    }

    @Test
    public void invalidMove7() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.EIGHTS);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SEVENTH);
        Rook rook = boardManager.getPiece(StatField.EIGHTS, StatField.EIGHTS);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FIFTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FOURTH);
       boardManager.makeMove(knight, StatField.SEVENTH, StatField.FIFTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.EIGHTS);

        boardManager.makeMove(rook, StatField.SEVENTH, StatField.EIGHTS);
        boardManager.makeMove(rook, StatField.EIGHTS, StatField.EIGHTS);

        boolean check = boardManager.makeMove(king, StatField.EIGHTS, StatField.SEVENTH);
        assertFalse(check);

    }

    // casteling test queen side white
    @Test
    public void invalidMove8() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.THIRD);
        assertFalse(check);
    }

    @Test
    public void invalidMove9() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        boardManager.makeMove(rook, StatField.SECOND, StatField.FIRST);
        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.THIRD);
        assertFalse(check);
    }

    @Test
    public void invalidMove11() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.THIRD, StatField.THIRD);
        boardManager.makeMove(queen, StatField.FOURTH, StatField.SECOND);
        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);

        boardManager.makeMove(rook, StatField.SECOND, StatField.FIRST);
        boardManager.makeMove(rook, StatField.FIRST, StatField.FIRST);

        boolean check = boardManager.makeMove(king, StatField.FIRST, StatField.THIRD);
        assertFalse(check);
    }

    // casteling test queen side black

    @Test
    public void invalidMove12() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        boolean check = boardManager.makeMove(king, StatField.EIGHTS, StatField.THIRD);
        assertFalse(check);
    }

    @Test
    public void invalidMove13() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.EIGHTS, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.EIGHTS, StatField.FIRST);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.SIXTH, StatField.THIRD);
        boardManager.makeMove(queen, StatField.SEVENTH, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(rook, StatField.SEVENTH, StatField.FIRST);
        boolean check = boardManager.makeMove(king, StatField.EIGHTS, StatField.THIRD);
        assertFalse(check);
    }

    @Test
    public void invalidMove14() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.EIGHTS, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.EIGHTS, StatField.FIRST);

        boardManager.makeMove(pawn, StatField.FIFTH, StatField.FOURTH);
        boardManager.makeMove(bishop, StatField.SIXTH, StatField.FIFTH);
       boardManager.makeMove(knight, StatField.SIXTH, StatField.THIRD);
        boardManager.makeMove(queen, StatField.SEVENTH, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);

        boardManager.makeMove(rook, StatField.SEVENTH, StatField.FIRST);
        boardManager.makeMove(rook, StatField.EIGHTS, StatField.FIRST);

        boolean check = boardManager.makeMove(king, StatField.EIGHTS, StatField.THIRD);
        assertFalse(check);
    }
}
