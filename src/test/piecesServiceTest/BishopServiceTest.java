package src.test.piecesServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.pieces.Bishop;
import src.main.entity.pieces.Knight;
import src.main.entity.pieces.Pawn;
import src.main.stat.statVar.StatField;

public class BishopServiceTest {
    private BoardManager boardManager;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
    }

    @Test
    public void validMove() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check = boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
        assertTrue(check);
    }

    @Test
    public void validMove1() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check = boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
        boolean check1 = boardManager.makeMove(bishop, StatField.FOURTH, StatField.SIXTH);
        assertTrue(check && check1);
    }

    @Test
    public void validMove2() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check = boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
        boolean check2 = boardManager.makeMove(bishop, StatField.FIRST, StatField.THIRD);
        assertTrue(check && check2);
    }

    @Test
    public void validMove3() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.SECOND);
        boolean check = boardManager.makeMove(bishop, StatField.THIRD, StatField.FIRST);
        assertTrue(check);
    }

    @Test
    public void invalidMove() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        boolean check = boardManager.makeMove(bishop, StatField.THIRD, StatField.FIRST);
        assertFalse(check);
    }

    @Test
    public void invalidMove1() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        boolean check = boardManager.makeMove(bishop, StatField.FIRST, StatField.THIRD);
        assertFalse(check);
    }

    @Test
    public void invalidMove2() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.SECOND);
        boolean check = boardManager.makeMove(bishop, StatField.THIRD, StatField.FIFTH);
        assertFalse(check);
    }
    
    // test for when the bishop is blocked by a knight
    @Test
    public void invalidMove3() {
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Pawn pawn1 = boardManager.getPiece(StatField.SEVENTH, StatField.FOURTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);

        boardManager.makeMove(pawn, StatField.THIRD, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.SIXTH, StatField.FOURTH);

       boardManager.makeMove(knight, StatField.SECOND, StatField.FOURTH);
        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FOURTH);

        boolean check = boardManager.makeMove(bishop, StatField.FIRST, StatField.THIRD );

        assertFalse(check);
    }

}
