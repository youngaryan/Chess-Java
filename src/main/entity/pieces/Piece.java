package src.main.entity.pieces;

public abstract class Piece {
    private boolean isWhite; // Indicates whether the pawn is white or black
    private int currentRow; // The current row on the chessboard
    private int currentCol; // The current column on the chessboard
    private boolean hasMoved;

    public Piece(boolean isWhite, int currentRow, int currentCol) {
        this.isWhite = isWhite;
        this.currentRow = currentRow;
        this.currentCol = currentCol;
    }

    public Piece(boolean isWhite, int currentRow, int currentCol, boolean hasMoved) {
        this.isWhite = isWhite;
        this.currentRow = currentRow;
        this.currentCol = currentCol;
        this.hasMoved = hasMoved;
    }

    public Piece() {
    }

    public abstract char getSymbol();



    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentCol(int currentCol) {
        this.currentCol = currentCol;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

}
