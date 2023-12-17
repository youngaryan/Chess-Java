package src.test.piecesServiceTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Queen;
import src.main.service.piecesService.bishopService.BishopService;
import src.main.service.piecesService.bishopService.BishopServiceImpl;
import src.main.service.piecesService.pawnService.PawnService;
import src.main.service.piecesService.pawnService.PawnServiceImpl;
import src.main.service.piecesService.queenService.QueenService;
import src.main.service.piecesService.queenService.QueenServiceImpl;
import src.main.service.piecesService.rookService.RookService;
import src.main.service.piecesService.rookService.RookServiceImpl;
import src.main.stat.statVar.StatField;

public class QueenServiceTest {
    private QueenService queenService;
    private BishopService bishopService;
    private RookService rookService;
    private BoardManager boardManager;
    private PawnService pawnService;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
        bishopService = new BishopServiceImpl(boardManager);
        rookService = new RookServiceImpl(boardManager);
        pawnService = new PawnServiceImpl(boardManager);
        queenService = new QueenServiceImpl(bishopService, rookService);
    }

    @Test
    public void validmove() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check = queenService.moveQueen(queen, StatField.THIRD, StatField.FOURTH);
        assertTrue(check);
    }

    @Test
    public void validmove1() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FOURTH);
        boolean check = queenService.moveQueen(queen, StatField.THIRD, StatField.FOURTH);
        boolean check1 = queenService.moveQueen(queen, StatField.FIRST, StatField.FOURTH);
        assertTrue(check && check1);
    }

    @Test
    public void validmove2() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = queenService.moveQueen(queen, StatField.SECOND, StatField.FIFTH);
        assertTrue(check);
    }

    @Test
    public void validmove3() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.THIRD);

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.THIRD);
        boolean check = queenService.moveQueen(queen, StatField.SECOND, StatField.THIRD);
        assertTrue(check);
    }

    @Test
    public void invalidMove() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = queenService.moveQueen(queen, StatField.SECOND, StatField.THIRD);

        assertFalse(check);
    }

    @Test
    public void invalidMove1() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = queenService.moveQueen(queen, StatField.SECOND, StatField.FOURTH);

        assertFalse(check);
    }

    @Test
    public void invalidMove2() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = queenService.moveQueen(queen, StatField.FIRST, StatField.FOURTH);

        assertFalse(check);
    }

    @Test
    public void invalidMove3() {
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);
        boolean check = queenService.moveQueen(queen, StatField.FIRST, StatField.EIGHTS);

        assertFalse(check);
    }
}
