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
import src.main.service.piecesService.KnightService.KnightService;
import src.main.service.piecesService.KnightService.KnightServiceImpl;
import src.main.service.piecesService.bishopService.BishopService;
import src.main.service.piecesService.bishopService.BishopServiceImpl;
import src.main.service.piecesService.kingService.KingService;
import src.main.service.piecesService.kingService.KingServiceImpl;
import src.main.service.piecesService.pawnService.PawnService;
import src.main.service.piecesService.pawnService.PawnServiceImpl;
import src.main.service.piecesService.queenService.QueenService;
import src.main.service.piecesService.queenService.QueenServiceImpl;
import src.main.service.piecesService.rookService.RookService;
import src.main.service.piecesService.rookService.RookServiceImpl;
import src.main.stat.statVar.StatField;

public class KingServiceTest {
    private KingService kingService;
    private BoardManager boardManager;
    private PawnService pawnService;
    private BishopService bishopService;
    private KnightService knightService;
    private RookService rookService;
    private QueenService queenService;

    @Before
    public void setUp() {
        boardManager = new BoardManager();
        kingService = new KingServiceImpl(boardManager);
        pawnService = new PawnServiceImpl(boardManager);
        bishopService = new BishopServiceImpl(boardManager);
        knightService = new KnightServiceImpl(boardManager);
        rookService = new RookServiceImpl(boardManager);
        queenService = new QueenServiceImpl(bishopService, rookService);
    }

    @Test
    public void validMove() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.SECOND, StatField.FIFTH);
        assertTrue(check);
    }

    @Test
    public void validMove1() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.SECOND, StatField.FIFTH);
        boolean check1 = kingService.moveKing(king, StatField.THIRD, StatField.FIFTH);
        assertTrue(check && check1);
    }

    @Test
    public void validMove2() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.SECOND, StatField.FIFTH);
        boolean check1 = kingService.moveKing(king, StatField.THIRD, StatField.FOURTH);
        assertTrue(check && check1);
    }

    @Test
    public void validMove3() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        // move the pawn to open up space for the king
        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.SECOND, StatField.FIFTH);
        boolean check1 = kingService.moveKing(king, StatField.FIRST, StatField.FIFTH);
        assertTrue(check && check1);
    }

    // casteling test knigside white
    @Test
    public void validMove4() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SEVENTH);

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SECOND, StatField.FIFTH);

        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.SEVENTH);
        assertTrue(check);

    }

    @Test
    public void validMove5() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FIFTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SEVENTH);

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SECOND, StatField.FIFTH);

        kingService.moveKing(king, StatField.FIRST, StatField.SEVENTH);

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

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SEVENTH, StatField.FIFTH);

        boolean check = kingService.moveKing(king, StatField.EIGHTS, StatField.SEVENTH);
        assertTrue(check);

    }

    @Test
    public void validMove7() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FIFTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.SIXTH);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SEVENTH);

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SEVENTH, StatField.FIFTH);

        kingService.moveKing(king, StatField.EIGHTS, StatField.SEVENTH);
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

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.THIRD, StatField.THIRD);
        queenService.moveQueen(queen, StatField.SECOND, StatField.FOURTH);
        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.THIRD);

        assertTrue(check);

    }

    @Test
    public void validMove9() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SECOND, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.FIRST, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.FIRST, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.FIRST, StatField.FOURTH);

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.THIRD, StatField.THIRD);
        queenService.moveQueen(queen, StatField.SECOND, StatField.FOURTH);

        kingService.moveKing(king, StatField.FIRST, StatField.THIRD);
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

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.SIXTH, StatField.THIRD);
        queenService.moveQueen(queen, StatField.SEVENTH, StatField.FOURTH);

        boolean check = kingService.moveKing(king, StatField.EIGHTS, StatField.THIRD);
        assertTrue(check);

    }

    @Test
    public void validMove11() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        Pawn pawn = boardManager.getPiece(StatField.SEVENTH, StatField.FOURTH);
        Bishop bishop = boardManager.getPiece(StatField.EIGHTS, StatField.THIRD);
        Knight knight = boardManager.getPiece(StatField.EIGHTS, StatField.SECOND);
        Queen queen = boardManager.getPiece(StatField.EIGHTS, StatField.FOURTH);

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.SIXTH, StatField.THIRD);
        queenService.moveQueen(queen, StatField.SEVENTH, StatField.FOURTH);

        kingService.moveKing(king, StatField.EIGHTS, StatField.THIRD);

        Piece[][] board = boardManager.getChessBoard();

        assertTrue(board[StatField.EIGHTS][StatField.FOURTH] instanceof Rook
                && board[StatField.EIGHTS][StatField.FIRST] == null);

    }

    @Test
    public void invalidMove() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.SEVENTH);
        assertFalse(check);
    }

    @Test
    public void invalidMove1() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.SECOND);
        assertFalse(check);
    }

    @Test
    public void invalidMove2() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.SECOND, StatField.FIFTH);
        assertFalse(check);
    }

    // casteling test knigside white
    @Test
    public void invalidMove3() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.SEVENTH);
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

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SECOND, StatField.FIFTH);
        pawnService.movePawn(pawn1, StatField.FOURTH, StatField.EIGHTS);

        rookService.moveRook(rook, StatField.SECOND, StatField.EIGHTS);
        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.SEVENTH);
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

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SECOND, StatField.FIFTH);
        pawnService.movePawn(pawn1, StatField.FOURTH, StatField.EIGHTS);

        rookService.moveRook(rook, StatField.SECOND, StatField.EIGHTS);
        rookService.moveRook(rook, StatField.FIRST, StatField.EIGHTS);

        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.SEVENTH);
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

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SEVENTH, StatField.FIFTH);
        pawnService.movePawn(pawn1, StatField.FIFTH, StatField.EIGHTS);

        rookService.moveRook(rook, StatField.SEVENTH, StatField.EIGHTS);
        boolean check = kingService.moveKing(king, StatField.EIGHTS, StatField.SEVENTH);
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

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FIFTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FOURTH);
        knightService.moveKnight(knight, StatField.SEVENTH, StatField.FIFTH);
        pawnService.movePawn(pawn1, StatField.FIFTH, StatField.EIGHTS);

        rookService.moveRook(rook, StatField.SEVENTH, StatField.EIGHTS);
        rookService.moveRook(rook, StatField.EIGHTS, StatField.EIGHTS);

        boolean check = kingService.moveKing(king, StatField.EIGHTS, StatField.SEVENTH);
        assertFalse(check);

    }

    // casteling test queen side white
    @Test
    public void invalidMove8() {
        King king = boardManager.getPiece(StatField.FIRST, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.THIRD);
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

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.THIRD, StatField.THIRD);
        queenService.moveQueen(queen, StatField.SECOND, StatField.FOURTH);
        pawnService.movePawn(pawn1, StatField.FOURTH, StatField.FIRST);

        rookService.moveRook(rook, StatField.SECOND, StatField.FIRST);
        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.THIRD);
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

        pawnService.movePawn(pawn, StatField.FOURTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.THIRD, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.THIRD, StatField.THIRD);
        queenService.moveQueen(queen, StatField.FOURTH, StatField.SECOND);
        pawnService.movePawn(pawn1, StatField.FOURTH, StatField.FIRST);

        rookService.moveRook(rook, StatField.SECOND, StatField.FIRST);
        rookService.moveRook(rook, StatField.FIRST, StatField.FIRST);

        boolean check = kingService.moveKing(king, StatField.FIRST, StatField.THIRD);
        assertFalse(check);
    }

    // casteling test queen side black

    @Test
    public void invalidMove12() {
        King king = boardManager.getPiece(StatField.EIGHTS, StatField.FIFTH);
        boolean check = kingService.moveKing(king, StatField.EIGHTS, StatField.THIRD);
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

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.SIXTH, StatField.THIRD);
        queenService.moveQueen(queen, StatField.SEVENTH, StatField.FOURTH);
        pawnService.movePawn(pawn1, StatField.FIFTH, StatField.FIRST);

        rookService.moveRook(rook, StatField.SEVENTH, StatField.FIRST);
        boolean check = kingService.moveKing(king, StatField.EIGHTS, StatField.THIRD);
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

        pawnService.movePawn(pawn, StatField.FIFTH, StatField.FOURTH);
        bishopService.moveBishop(bishop, StatField.SIXTH, StatField.FIFTH);
        knightService.moveKnight(knight, StatField.SIXTH, StatField.THIRD);
        queenService.moveQueen(queen, StatField.SEVENTH, StatField.FOURTH);
        pawnService.movePawn(pawn1, StatField.FIFTH, StatField.FIRST);

        rookService.moveRook(rook, StatField.SEVENTH, StatField.FIRST);
        rookService.moveRook(rook, StatField.EIGHTS, StatField.FIRST);

        boolean check = kingService.moveKing(king, StatField.EIGHTS, StatField.THIRD);
        assertFalse(check);
    }
}
