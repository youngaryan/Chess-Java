package src.main.entity.pieces;

public class Bishop extends Piece {
    public Bishop() {
        super();
    }

    public Bishop(boolean isWhite, int currentRow, int currentCol) {
        super(isWhite, currentRow, currentCol);
    }

    @Override
    public char getSymbol() {
        return 'B';
    }
}
