package src.test.piecesServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.pieces.Knight;
import src.main.entity.pieces.Pawn;
import src.main.stat.statVar.StatField;

public class KnightServiceTest {
    private BoardManager boardManager;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
    }

    @Test
    public void validMove() {
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        boolean check =boardManager.makeMove(knight, StatField.THIRD, StatField.THIRD);

        assertTrue(check);
    }

    @Test
    public void validMove1() {
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        boolean check =boardManager.makeMove(knight, StatField.THIRD, StatField.FIRST);

        assertTrue(check);
    }

    @Test
    public void validMove3() {
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SECOND);
        boolean check =boardManager.makeMove(knight, StatField.SIXTH, StatField.FIRST);

        assertTrue(check);
    }

    @Test
    public void validMove4() {
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check =boardManager.makeMove(knight, StatField.SECOND, StatField.FOURTH);

        assertTrue(check);
    }

    @Test
    public void validMove5() {
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check =boardManager.makeMove(knight, StatField.SECOND, StatField.FOURTH);
        boolean check1 =boardManager.makeMove(knight, StatField.FOURTH, StatField.THIRD);
        assertTrue(check && check1);
    }

    @Test
    public void invalidMove() {
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        boolean check =boardManager.makeMove(knight, StatField.THIRD, StatField.SECOND);

        assertFalse(check);
    }

    @Test
    public void invalidMove1() {
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        boolean check =boardManager.makeMove(knight, StatField.SECOND, StatField.FOURTH);

        assertFalse(check);
    }
}
