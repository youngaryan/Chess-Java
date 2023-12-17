package src.test.gameServiceTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import src.main.BoardManager;
import src.main.entity.game.Move;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Rook;
import src.main.service.gameService.MoveService.MoveService;
import src.main.service.gameService.MoveService.MoveServiceImpl;
import src.main.service.piecesService.pawnService.PawnService;
import src.main.service.piecesService.pawnService.PawnServiceImpl;
import src.main.service.piecesService.rookService.RookService;
import src.main.service.piecesService.rookService.RookServiceImpl;
import src.main.stat.statVar.StatField;

public class MoveServiceTest {
    private MoveService moveService;
    private BoardManager boardManager;
    private PawnService pawnService;
    private RookService rookService;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
        moveService = new MoveServiceImpl();
        pawnService = new PawnServiceImpl(boardManager);
        rookService = new RookServiceImpl(boardManager);
    }

    @Test
    public void findAllMovesTest() {
        assertTrue(moveService.findAllMoves().isEmpty());
    }

    @Test
    public void addMoveTest() {
        Pawn pawnWhite = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawnBlack = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        pawnService.movePawn(pawnWhite, StatField.FOURTH, StatField.FIRST);
        pawnService.movePawn(pawnBlack, StatField.FIFTH, StatField.FIRST);
        rookService.moveRook(rook, StatField.THIRD, StatField.FIRST);

        assertTrue(moveService.findAllMoves().size() == 3);

    }

    @Test
    public void findAllMovesPlayerWhiteTest() {
        Pawn pawnWhite = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawnBlack = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        pawnService.movePawn(pawnWhite, StatField.FOURTH, StatField.FIRST);
        pawnService.movePawn(pawnBlack, StatField.FIFTH, StatField.FIRST);
        rookService.moveRook(rook, StatField.THIRD, StatField.FIRST);

        assertTrue(moveService.findAllMovesPlayerWhite().size() == 2);

    }

    @Test
    public void findAllMovesPlayerBlackTest() {
        Pawn pawnWhite = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawnBlack = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        pawnService.movePawn(pawnWhite, StatField.FOURTH, StatField.FIRST);
        pawnService.movePawn(pawnBlack, StatField.FIFTH, StatField.FIRST);
        rookService.moveRook(rook, StatField.THIRD, StatField.FIRST);

        assertTrue(moveService.findAllMovesPlayerBlack().size() == 1);

    }

    @Test
    public void findLastMoveTest() {
        Pawn pawnWhite = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawnBlack = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        pawnService.movePawn(pawnWhite, StatField.FOURTH, StatField.FIRST);
        pawnService.movePawn(pawnBlack, StatField.FIFTH, StatField.FIRST);
        rookService.moveRook(rook, StatField.THIRD, StatField.FIRST);

        assertTrue(moveService.findLastMove().getPiece() instanceof Rook);

    }

    @Test
    public void deleteLastMoveTest() {

        Pawn pawnWhite = boardManager.getPiece(StatField.SECOND, StatField.FIRST);
        Pawn pawnBlack = boardManager.getPiece(StatField.SEVENTH, StatField.FIRST);
        Rook rook = boardManager.getPiece(StatField.FIRST, StatField.FIRST);

        pawnService.movePawn(pawnWhite, StatField.FOURTH, StatField.FIRST);
        pawnService.movePawn(pawnBlack, StatField.FIFTH, StatField.FIRST);
        rookService.moveRook(rook, StatField.THIRD, StatField.FIRST);

        boolean check = moveService.deleteLastMove();

        Move move = moveService.findLastMove();

        assertTrue(move.getPiece() instanceof Pawn && !move.getPiece().isWhite() && check);

    }
}
