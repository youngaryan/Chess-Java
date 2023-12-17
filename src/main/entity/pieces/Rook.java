package src.main.entity.pieces;

public class Rook extends Piece {

    public Rook() {
        super();
    }

    public Rook(boolean isWhite, int currentRow, int currentCol) {
        super(isWhite, currentRow, currentCol);

    }

    public Rook(boolean isWhite, int currentRow, int currentCol, boolean hasMoved) {
        super(isWhite, currentRow, currentCol, hasMoved);
    }

    @Override
    public char getSymbol() {
        return 'R';
    }

}
