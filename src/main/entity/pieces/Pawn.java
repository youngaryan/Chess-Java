package src.main.entity.pieces;

public class Pawn extends Piece {
    private boolean ableToEnPassant;

    public Pawn(boolean isWhite, int currentRow, int currentCol,boolean hasMoved) {
        super(isWhite, currentRow, currentCol,hasMoved);
    }

    public Pawn() {
        super();
    }
    
    @Override
    public char getSymbol() {
        return 'P';
    }

    public boolean isAbleToEnPassant() {
        return ableToEnPassant;
    }

    public void setAbleToEnPassant(boolean ableToEnPassant) {
        this.ableToEnPassant = ableToEnPassant;
    }
    

}
