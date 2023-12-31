package src.main.entity.game;

import java.util.Objects;

import src.main.entity.pieces.Piece;

public class Move {
    int moveNumber;
    private Piece piece;
    private int startRow;
    private int startCol;
    private int endRow;
    private int endCol;
    private int player;
    private Piece capturedPiece;

    public Move(Piece piece, int startRow, int startCol, int endRow, int endCol, int player) {
        this.piece = piece;
        this.startRow = startRow;
        this.startCol = startCol;
        this.endRow = endRow;
        this.endCol = endCol;
        this.player = player;
        this.capturedPiece = null;
    }

    public Move() {
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getStartCol() {
        return startCol;
    }

    public void setStartCol(int startCol) {
        this.startCol = startCol;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public int getEndCol() {
        return endCol;
    }

    public void setEndCol(int endCol) {
        this.endCol = endCol;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getMoveNumber() {
        return moveNumber;
    }

    public void setMoveNumber(int moveNumber) {
        this.moveNumber = moveNumber;
    }

    @Override
    public String toString() {
        return "Move [moveNumber=" + moveNumber + ", piece=" + piece.getClass().getSimpleName() + ", startRow="
                + (startRow + 1) + ", startCol="
                + (startCol + 1) + ", endRow=" + (endRow + 1) + ", endCol=" + (endCol + 1) + ", player="
                + (player == 0 ? "White" : "Black") + ", capturedPiece="
                + (capturedPiece == null ? "No Cpature" : capturedPiece.getClass().getSimpleName()) + "]";
    }

    public Piece getCapturedPiece() {
        return capturedPiece;
    }

    public void setCapturedPiece(Piece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Move otherMove = (Move) obj;

        return Objects.equals(this.piece, otherMove.piece) &&
                this.startRow == otherMove.startRow &&
                this.startCol == otherMove.startCol &&
                this.endRow == otherMove.endRow &&
                this.endCol == otherMove.endCol;
    }

}
