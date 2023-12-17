package src.main.entity.pieces;

public class Knight extends Piece {

    public Knight(boolean isWhite, int initialRow, int initialCol) {
        super(isWhite, initialRow, initialCol);
    }

    public Knight() {
        super();
    }

    @Override
    public char getSymbol() {
        return 'N';
    }
}
