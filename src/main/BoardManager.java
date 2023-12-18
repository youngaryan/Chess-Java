package src.main;

import java.util.ArrayList;
import java.util.List;

import src.main.entity.game.Move;
import src.main.entity.pieces.Bishop;
import src.main.entity.pieces.King;
import src.main.entity.pieces.Knight;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Queen;
import src.main.entity.pieces.Rook;
import src.main.service.gameService.MoveService.MoveService;
import src.main.service.gameService.MoveService.MoveServiceImpl;
import src.main.service.gameService.checkService.CheckService;
import src.main.service.gameService.checkService.CheckServiceImpl;
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

public class BoardManager {
        private Piece[][] chessBoard;
        // TODO make is validmove a class and make other classes to extend it
        private PawnService pawnService;
        private RookService rookService;
        private KingService kingService;
        private BishopService bishopService;
        private QueenService queenService;
        private KnightService knightService;
        private MoveService moveService;
        private CheckService checkService;
        // Add other piece services as needed

        public BoardManager() {
                initializeServices(this);
                initializeBoard();
        }

        private void initializeServices(BoardManager boardManager) {
                rookService = new RookServiceImpl(boardManager);
                kingService = new KingServiceImpl(boardManager);
                bishopService = new BishopServiceImpl(boardManager);
                queenService = new QueenServiceImpl(bishopService, rookService);
                knightService = new KnightServiceImpl(boardManager);
                moveService = new MoveServiceImpl();
                checkService = new CheckServiceImpl(boardManager);
                pawnService = new PawnServiceImpl(boardManager);

        }

        public <T extends Piece> T getPiece(int row, int col) {
                T piece = (T) chessBoard[row][col];

                if (piece == null) {
                        return null;
                }
                // Use reflection to get the class type of the piece
                Class<?> pieceClass = piece.getClass();

                // Check if the piece is of the expected type
                if (Piece.class.isAssignableFrom(pieceClass)) {
                        // Cast the piece to the expected type
                        return piece;
                } else {
                        // The piece is not of the expected type
                        return null;
                }
        }

        private void initializeBoard() {
                chessBoard = new Piece[8][8];
                // creating white rooks
                chessBoard[StatField.FIRST][StatField.FIRST] = rookService.createRook(true, StatField.FIRST,
                                StatField.FIRST);
                chessBoard[StatField.FIRST][StatField.EIGHTS] = rookService.createRook(true, StatField.FIRST,
                                StatField.EIGHTS);
                // creating black rooks
                chessBoard[StatField.EIGHTS][StatField.FIRST] = rookService.createRook(false, StatField.EIGHTS,
                                StatField.FIRST);
                chessBoard[StatField.EIGHTS][StatField.EIGHTS] = rookService.createRook(false, StatField.EIGHTS,
                                StatField.EIGHTS);

                // creating white king
                chessBoard[StatField.FIRST][StatField.FIFTH] = kingService.createKing(true, StatField.FIRST,
                                StatField.FIFTH);
                // creating black king
                chessBoard[StatField.EIGHTS][StatField.FIFTH] = kingService.createKing(false, StatField.EIGHTS,
                                StatField.FIFTH);

                // creating white bishops
                chessBoard[StatField.FIRST][StatField.THIRD] = bishopService.createBishop(true, StatField.FIRST,
                                StatField.THIRD);
                chessBoard[StatField.FIRST][StatField.SIXTH] = bishopService.createBishop(true, StatField.FIRST,
                                StatField.SIXTH);

                // creating black bishops
                chessBoard[StatField.EIGHTS][StatField.THIRD] = bishopService.createBishop(false, StatField.EIGHTS,
                                StatField.THIRD);
                chessBoard[StatField.EIGHTS][StatField.SIXTH] = bishopService.createBishop(false, StatField.EIGHTS,
                                StatField.SIXTH);

                // creating white queen
                chessBoard[StatField.FIRST][StatField.FOURTH] = queenService.createQueen(true, StatField.FIRST,
                                StatField.FOURTH);
                // creating black queen
                chessBoard[StatField.EIGHTS][StatField.FOURTH] = queenService.createQueen(false, StatField.EIGHTS,
                                StatField.FOURTH);

                // creating white knights
                chessBoard[StatField.FIRST][StatField.SECOND] = knightService.createKnight(true, StatField.FIRST,
                                StatField.SECOND);
                chessBoard[StatField.FIRST][StatField.SEVENTH] = knightService.createKnight(true, StatField.FIRST,
                                StatField.SEVENTH);

                // creating black knights
                chessBoard[StatField.EIGHTS][StatField.SECOND] = knightService.createKnight(false, StatField.EIGHTS,
                                StatField.SECOND);
                chessBoard[StatField.EIGHTS][StatField.SEVENTH] = knightService.createKnight(false, StatField.EIGHTS,
                                StatField.SEVENTH);

                // creating pawns
                for (int col = 0; col < 8; col++) {
                        // white pawns
                        chessBoard[StatField.SECOND][col] = pawnService.createPawn(true, StatField.SECOND, col);

                        // black pawns
                        chessBoard[StatField.SEVENTH][col] = pawnService.createPawn(false, StatField.SEVENTH, col);
                }
        }

        public Piece[][] getChessBoard() {
                return chessBoard;
        }

        public <T extends Piece> Piece[][] movePieceilligal(T piece, int newRow, int newCol) {
                int currentRow = piece.getCurrentRow();
                int currentCol = piece.getCurrentCol();
                int player;
                if (piece.isWhite()) {
                        player = StatField.PLAYER_WHITE;
                } else {
                        player = StatField.PLAYER_BLACK;
                }

                // enpasunt
                if (piece instanceof Pawn) {
                        Pawn pawn = (Pawn) piece;
                        if (pawn.isAbleToEnPassant()) {
                                pawn.setAbleToEnPassant(false);
                                chessBoard[currentRow][newCol] = null;
                        }
                }

                Move move = new Move(piece, piece.getCurrentRow(), piece.getCurrentCol(), newRow, newCol, player);

                move.setCapturedPiece(chessBoard[newRow][newCol]);

                chessBoard[currentRow][currentCol] = null; // Remove the piece from the current position
                chessBoard[newRow][newCol] = piece;

                moveService.addMove(move);

                piece.setCurrentRow(newRow);
                piece.setCurrentCol(newCol);

                return chessBoard;
        }

        public <T extends Piece> Piece[][] movePiece(T piece, int newRow, int newCol) {
                if (!isLegalMove(piece, newRow, newCol)) {
                        return chessBoard;
                }

                int currentRow = piece.getCurrentRow();
                int currentCol = piece.getCurrentCol();
                int player;
                if (piece.isWhite()) {
                        player = StatField.PLAYER_WHITE;
                } else {
                        player = StatField.PLAYER_BLACK;
                }

                // enpasunt
                if (piece instanceof Pawn) {
                        Pawn pawn = (Pawn) piece;
                        if (pawn.isAbleToEnPassant()) {
                                pawn.setAbleToEnPassant(false);
                                chessBoard[currentRow][newCol] = null;
                        }
                }

                Move move = new Move(piece, piece.getCurrentRow(), piece.getCurrentCol(), newRow, newCol, player);

                move.setCapturedPiece(chessBoard[newRow][newCol]);

                chessBoard[currentRow][currentCol] = null; // Remove the piece from the current position
                chessBoard[newRow][newCol] = piece;

                moveService.addMove(move);
                piece.setCurrentRow(newRow);
                piece.setCurrentCol(newCol);

                return chessBoard;
        }

        public Piece[][] promotePice(Pawn pawn, Class<? extends Piece> newPiece, int newRow, int newCol) {
                int currentRow = pawn.getCurrentRow();
                int currentCol = pawn.getCurrentCol();

                try {
                        chessBoard[currentRow][currentCol] = null; // Remove the piece from the current position
                        Piece piece = newPiece.getDeclaredConstructor(boolean.class, int.class, int.class)
                                        .newInstance(pawn.isWhite(), newRow, newCol);
                        chessBoard[newRow][newCol] = piece;

                } catch (Exception e) {
                        e.printStackTrace();
                }
                return chessBoard;

        }

        public Move findTheLastMove() {
                return moveService.findLastMove();
        }

        public List<Move> findPossMovesByPices(Piece piece) {
                List<Move> moves = new ArrayList<>();
                Move move;
                int player;
                int startRow = piece.getCurrentRow();
                int startCol = piece.getCurrentCol();

                if (piece.isWhite()) {
                        player = StatField.PLAYER_WHITE;
                } else {
                        player = StatField.PLAYER_BLACK;
                }

                if (piece.getClass().getSimpleName().equals("Pawn")) {
                        Pawn pawn = (Pawn) piece;
                        for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                        if (pawnService.isValidMove(pawn, i, j)) {
                                                move = new Move();
                                                move.setPiece(pawn);
                                                move.setStartRow(startRow);
                                                move.setStartCol(startCol);
                                                move.setEndRow(i);
                                                move.setEndCol(j);
                                                move.setPlayer(player);
                                                moves.add(move);
                                        }
                                }

                        }
                } else if (piece.getClass().getSimpleName().equals("Rook")) {
                        Rook rook = (Rook) piece;
                        for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                        if (rookService.isValidMove(rook, i, j)) {
                                                move = new Move();
                                                move.setPiece(rook);
                                                move.setStartRow(startRow);
                                                move.setStartCol(startCol);
                                                move.setEndRow(i);
                                                move.setEndCol(j);
                                                move.setPlayer(player);
                                                moves.add(move);
                                        }
                                }

                        }
                } else if (piece.getClass().getSimpleName().equals("Knight")) {
                        Knight knight = (Knight) piece;
                        for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                        if (knightService.isValidMove(knight, i, j)) {
                                                move = new Move();
                                                move.setPiece(knight);
                                                move.setStartRow(startRow);
                                                move.setStartCol(startCol);
                                                move.setEndRow(i);
                                                move.setEndCol(j);
                                                move.setPlayer(player);
                                                moves.add(move);
                                        }
                                }

                        }
                } else if (piece.getClass().getSimpleName().equals("Bishop")) {
                        Bishop bishop = (Bishop) piece;
                        for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                        if (bishopService.isValidMove(bishop, i, j)) {
                                                move = new Move();
                                                move.setPiece(bishop);
                                                move.setStartRow(startRow);
                                                move.setStartCol(startCol);
                                                move.setEndRow(i);
                                                move.setEndCol(j);
                                                move.setPlayer(player);
                                                moves.add(move);
                                        }
                                }

                        }
                } else if (piece.getClass().getSimpleName().equals("Queen")) {
                        Queen queen = (Queen) piece;
                        for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                        if (queenService.isValidMove(queen, i, j)) {
                                                move = new Move();
                                                move.setPiece(queen);
                                                move.setStartRow(startRow);
                                                move.setStartCol(startCol);
                                                move.setEndRow(i);
                                                move.setEndCol(j);
                                                move.setPlayer(player);
                                                moves.add(move);
                                        }
                                }

                        }
                } else if (piece.getClass().getSimpleName().equals("King")) {
                        King king = (King) piece;
                        for (int i = 0; i < 8; i++) {
                                for (int j = 0; j < 8; j++) {
                                        if (kingService.isValidMove(king, i, j)) {
                                                move = new Move();
                                                move.setPiece(king);
                                                move.setStartRow(startRow);
                                                move.setStartCol(startCol);
                                                move.setEndRow(i);
                                                move.setEndCol(j);
                                                move.setPlayer(player);
                                                moves.add(move);
                                        }
                                }

                        }
                }

                return moves;
        }

        public int isKingInCheckByColour(boolean isWhite) {
                return checkService.isKingInCheckByColour(isWhite);
        }

        public boolean isKingCheckMatebyColour(boolean isWhite) {
                return checkService.isKingCheckMatebyColour(isWhite);
        }

        public <T extends Piece> boolean isLegalMove(T piece, int newRow, int newCol) {
                movePieceilligal(piece, newRow, newCol);

                if (isKingInCheckByColour(piece.isWhite()) > 0) {
                        undoMove();
                        return false;
                }
                undoMove();
                return true;
        }

        public Piece[][] undoMove() {

                Move lastMove =findTheLastMove();

                int newRow = lastMove.getEndRow();
                int newCol = lastMove.getEndCol();
                int currentRow = lastMove.getStartRow();
                int currentCol = lastMove.getStartCol();
                Piece piece = lastMove.getPiece();

                chessBoard[currentRow][currentCol] = piece;

                chessBoard[newRow][newCol] = lastMove.getCapturedPiece();

                piece.setCurrentRow(currentRow);
                piece.setCurrentCol(currentCol);
                
                moveService.deleteLastMove();

                return chessBoard;
        }

        public boolean makeMove(Piece piece, int newRow, int newCol) {
                if (piece instanceof Pawn && pawnService.isValidMove((Pawn) piece, newRow, newCol)) {
                        movePiece((Pawn) piece, newRow, newCol);
                        return true;
                } else if (piece instanceof Rook && rookService.isValidMove((Rook) piece, newRow, newCol)) {
                        movePiece((Rook) piece, newRow, newCol);
                        return true;
                } else if (piece instanceof Knight && knightService.isValidMove((Knight) piece, newRow, newCol)) {
                       movePiece((Knight) piece, newRow, newCol);
                        return true;
                } else if (piece instanceof Bishop && bishopService.isValidMove((Bishop) piece, newRow, newCol)) {
                        movePiece((Bishop) piece, newRow, newCol);
                        return true;
                } else if (piece instanceof Queen && queenService.isValidMove((Queen) piece, newRow, newCol)) {
                        movePiece((Queen) piece, newRow, newCol);
                        return  true;
                } else if (piece instanceof King && kingService.isValidMove((King) piece, newRow, newCol)) {
                        movePiece((King) piece, newRow, newCol);
                        return true;
                } else {
                        return false;
                }
        }

        public List<Move> findAllMoves() {
                return moveService.findAllMoves();
        }

        public boolean canCheckBeBlockedByColour(boolean isWhite) {
                Move checkingMove = checkService.findMovesCheckingKing(isWhite).get(0);

                List<int[]> path = checkService.findCheckPath(checkingMove);

                List<List<Move>> allpossMoves = checkService.findPossMovesByColour(isWhite);

                for (int[] square : path) {
                        int row = square[0];
                        int col = square[1];

                        for (List<Move> pieceMoves : allpossMoves) {
                                for (Move move : pieceMoves) {
                                        if (move.getEndRow() == row && move.getEndCol() == col) {
                                                if (isLegalMove(move.getPiece(), row, col)) {
                                                        return true;

                                                }
                                        }
                                }
                        }
                }
                return false;
        }

}
