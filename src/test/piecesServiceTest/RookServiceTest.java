package src.test.piecesServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Rook;
import src.main.stat.statVar.StatField;

public class RookServiceTest {
    private BoardManager boardManager;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
    }

    @Test
    public void validMove() {
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        // move the pawn to open up space for the rook
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIRST);

        boolean check = boardManager.makeMove(rook, StatField.THIRD, StatField.FIRST);
        assertTrue(check);
    }

    @Test
    public void validMove1() {
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        // move the pawn to open up space for the rook
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIRST);

        // move the rook then move it horizantally
        boolean check1 = boardManager.makeMove(rook, StatField.THIRD, StatField.FIRST);
        System.out.println(check1);
        boolean check2 = boardManager.makeMove(rook, StatField.THIRD, StatField.THIRD);
        System.out.println(check2);
        assertTrue(check1 && check2);
    }

    @Test
    public void validMove2() {
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        // move the pawn to open up space for the rook
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIRST);

        // move the rook then move it horizantally then capture black
        boolean check1 = boardManager.makeMove(rook, StatField.THIRD, StatField.FIRST);
        boolean check2 = boardManager.makeMove(rook, StatField.THIRD, StatField.THIRD);
        boolean check3 = boardManager.makeMove(rook, StatField.SEVENTH, StatField.THIRD);
        assertTrue(check1 && check2 && check3);
    }

    @Test
    public void invalidMove() {
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);
        boolean check = boardManager.makeMove(rook, StatField.FIRST, StatField.THIRD);
        assertFalse(check);
    }

    @Test
    public void invalidMove1() {
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);
        boolean check = boardManager.makeMove(rook, StatField.FIRST, StatField.FIRST);
        assertFalse(check);
    }

    @Test
    public void invalidMove2() {
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);
        boolean check = boardManager.makeMove(rook, StatField.FOURTH, StatField.FIRST);
        assertFalse(check);
    }

    @Test
    public void invalidMove3() {
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        // move the pawn to open up space for the rook
        boardManager.makeMove(pawn, StatField.FOURTH, StatField.FIRST);

        // move the rook then move it horizantally then capture black final rank (wrong a pawn is between the rook and the final rank)
        boolean check1 = boardManager.makeMove(rook, StatField.THIRD, StatField.FIRST);
        boolean check2 = boardManager.makeMove(rook, StatField.THIRD, StatField.THIRD);
        boolean check3 = boardManager.makeMove(rook, StatField.EIGHTS, StatField.THIRD);
        assertTrue((check1 && check2) && (!check3));

    }
}
