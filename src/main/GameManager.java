package src.main;

import java.util.List;
import java.util.Scanner;

import src.main.entity.game.Move;
import src.main.entity.pieces.Bishop;
import src.main.entity.pieces.Knight;
import src.main.entity.pieces.Pawn;
import src.main.entity.pieces.Piece;
import src.main.entity.pieces.Queen;
import src.main.entity.pieces.Rook;
import src.main.stat.statMethod.InputChecker;

public class GameManager {
    private BoardManager boardManager;
    private boolean whiteTurn;
    private Scanner scanner;

    public GameManager() {
        this.boardManager = new BoardManager();
        this.whiteTurn = true;
        this.scanner = new Scanner(System.in);
    }

    public void playGame() {
        while (!isGameOver()) {
            printBoard();

            int startRow, startCol, endRow, endCol;
            Piece piece;

            do {
                if (whiteTurn) {
                    System.out.println("White turn");
                } else {
                    System.out.println("Black trun");
                }
                System.out.print("Enter the starting row: ");
                startRow = scanner.nextInt() - 1;

                System.out.print("Enter the starting column: ");
                startCol = scanner.nextInt() - 1;

                System.out.print("Enter the ending row: ");
                endRow = scanner.nextInt() - 1;

                System.out.print("Enter the ending column: ");
                endCol = scanner.nextInt() - 1;

            } while (!isValid(startRow, startCol, endRow, endCol, whiteTurn));

            piece = boardManager.getPiece(startRow, startCol);

            if (piece instanceof Pawn && (endRow == 0 || endRow == 7)) {
                System.out.println("Pawn reached the end! Choose a piece for promotion:");
                System.out.println("1. Queen");
                System.out.println("2. Rook");
                System.out.println("3. Bishop");
                System.out.println("4. Knight");
                int choice = scanner.nextInt();

                Class<? extends Piece> promotionPieceClass = null;
                switch (choice) {
                    case 1:
                        promotionPieceClass = Queen.class;
                        break;
                    case 2:
                        promotionPieceClass = Rook.class;
                        break;
                    case 3:
                        promotionPieceClass = Bishop.class;
                        break;
                    case 4:
                        promotionPieceClass = Knight.class;
                        break;
                    default:
                        // Handle invalid input
                        break;
                }

                if (promotionPieceClass != null) {
                    boardManager.promotePice((Pawn) piece, promotionPieceClass, endRow, endCol);
                }
            } else {

                if (!boardManager.isLegalMove(piece, endRow, endCol)) {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                    System.out.println("wrong or illigal move try again, carefull of potential check or pin!");
                    continue;
                }
                boardManager.makeMove(piece, endRow, endCol);
            }
            whiteTurn = !whiteTurn;

        }
        printBoard();
        printGameResult();
        System.out.println("would you like to see the moves that have been played?(y/n) ");
        if (scanner.next().equalsIgnoreCase("y")) {
            List<Move> allMoves = boardManager.findAllMoves();

            // Print each move on a new line
            for (Move move : allMoves) {
                System.out.println(move);
            }

        }
    }

    private boolean isValid(int startRow, int startCol, int endRow, int endCol, boolean whiteTurn) {
        if (InputChecker.newGriddimentionChecker(endRow, endCol)
                && InputChecker.newGriddimentionChecker(startRow, startCol)
                && boardManager.getPiece(startRow, startCol).isWhite() == whiteTurn) {
            return true;
        }
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        System.out.println("Illigal");

        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        printBoard();
        return false;
    }

    private void printGameResult() {
        if (boardManager.isKingCheckMatebyColour(true)) {
            System.out.println("Black Won!");
        } else if (boardManager.isKingCheckMatebyColour(false)) {
            System.out.println("White won!");
        }
    }

    private void printBoard() {
        Piece[][] chessBoard = boardManager.getChessBoard();

        System.out.println(" +----------------");

        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + "| ");
            for (int j = 0; j < 8; j++) {
                Piece piece = chessBoard[i][j];
                if (piece == null) {
                    System.out.print(". ");
                } else if (piece.isWhite()) {
                    System.out.print(piece.getSymbol() + " ");
                } else {
                    System.out.print((char) (piece.getSymbol() + 32) + " ");
                }
            }
            System.out.println();
        }
        System.out.println(" +----------------");
        System.out.println("   1 2 3 4 5 6 7 8");
    }

    private boolean isGameOver() {
        if (boardManager.isKingCheckMatebyColour(true) || boardManager.isKingCheckMatebyColour(false)) {
            return true;
        }
        return false;
    }
}
