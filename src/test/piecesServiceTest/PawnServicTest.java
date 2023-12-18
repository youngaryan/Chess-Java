package src.test.piecesServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.pieces.Pawn;

import src.main.stat.statVar.StatField;

public class PawnServicTest {
    private BoardManager boardManager;

    @Before
    public void setUp() {
        boardManager = new BoardManager();

    }

    @Test
    public void validmove() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.THIRD, StatField.SECOND);
        assertTrue(check);
    }

    @Test
    public void validmove1() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.FOURTH, StatField.SECOND);
        assertTrue(check);
    }

    @Test
    public void validmove2() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.FOURTH, StatField.SECOND);
        check = boardManager.makeMove(pawn, StatField.FIFTH, StatField.SECOND);
        check = boardManager.makeMove(pawn, StatField.SIXTH, StatField.SECOND);
        check = boardManager.makeMove(pawn, StatField.SEVENTH, StatField.FIRST);
        assertTrue(check);
    }

    // enpaswnt
    @Test
    public void validmove3() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        Pawn pawn1 = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawn2 = boardManager.getPiece(StatField.SEVENTH, StatField.THIRD);

        boardManager.makeMove(pawn1, StatField.FOURTH, StatField.FIRST);
        boardManager.makeMove(pawn2, StatField.FIFTH, StatField.THIRD);

        boardManager.makeMove(pawn1, StatField.FIFTH, StatField.FIRST);
        boardManager.makeMove(pawn2, StatField.FOURTH, StatField.THIRD);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn2, StatField.THIRD, StatField.SECOND);

        assertTrue(check);

    }


    @Test
    public void invalidmove() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.FIFTH, StatField.SECOND);
        assertFalse(check);
    }

    @Test
    public void invalidmove1() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.THIRD, StatField.SECOND);
        check = boardManager.makeMove(pawn, StatField.THIRD, StatField.SECOND);
        assertFalse(check);
    }

    @Test
    public void invalidmove2() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.THIRD, StatField.SECOND);
        check = boardManager.makeMove(pawn, StatField.FIFTH, StatField.SECOND);
        assertFalse(check);
    }

    @Test
    public void invalidmove3() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.THIRD, StatField.FIRST);
        assertFalse(check);
    }

    @Test
    public void invalidmove4() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.THIRD, StatField.FIRST);
        assertFalse(check);
    }

    @Test
    public void invalidmove5() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boolean check = boardManager.makeMove(pawn, StatField.SECOND, StatField.SECOND);
        assertFalse(check);
    }

    @Test
    public void invalidmove6() {
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.SECOND);
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.SECOND);
        boolean check1 = boardManager.makeMove(pawn, StatField.SECOND, StatField.SECOND);
        assertFalse(check1);
    }
}
