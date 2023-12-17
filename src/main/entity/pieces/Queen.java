package src.main.entity.pieces;

public class Queen extends Piece {

    public Queen(boolean isWhite, int initialRow, int initialCol) {
        super(isWhite, initialRow, initialCol);
    }

    public Queen() {
        super();
    }


    @Override
    public char getSymbol() {
       return 'Q';
    }

}
