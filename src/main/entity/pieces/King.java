package src.main.entity.pieces;

public class King extends Piece {
    private boolean isCheck;

    public King() {
        super();
    }

    public King(boolean isWhite, int currentRow, int currentCol, boolean hasMoved) {
        super(isWhite, currentRow, currentCol, hasMoved);
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    @Override
    public char getSymbol() {
        return 'K';
    }

}
