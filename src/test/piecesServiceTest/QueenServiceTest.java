package src.test.piecesServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Queen;
import src.main.stat.statVar.StatField;

public class QueenServiceTest {
    private BoardManager boardManager;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
    }

    @Test
    public void validmove() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check = boardManager.makeMove(queen, StatField.THIRD, StatField.FOURTH);
        assertTrue(check);
    }

    @Test
    public void validmove1() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check = boardManager.makeMove(queen, StatField.THIRD, StatField.FOURTH);
        boolean check1 = boardManager.makeMove(queen, StatField.FIRST, StatField.FOURTH);
        assertTrue(check && check1);
    }

    @Test
    public void validmove2() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = boardManager.makeMove(queen, StatField.SECOND, StatField.FIFTH);
        assertTrue(check);
    }

    @Test
    public void validmove3() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.THIRD);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.THIRD);
        boolean check = boardManager.makeMove(queen, StatField.SECOND, StatField.THIRD);
        assertTrue(check);
    }

    @Test
    public void invalidMove() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = boardManager.makeMove(queen, StatField.SECOND, StatField.THIRD);

        assertFalse(check);
    }

    @Test
    public void invalidMove1() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = boardManager.makeMove(queen, StatField.SECOND, StatField.FOURTH);

        assertFalse(check);
    }

    @Test
    public void invalidMove2() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = boardManager.makeMove(queen, StatField.FIRST, StatField.FOURTH);

        assertFalse(check);
    }

    @Test
    public void invalidMove3() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = boardManager.makeMove(queen, StatField.FIRST, StatField.EIGHTS);

        assertFalse(check);
    }
}
