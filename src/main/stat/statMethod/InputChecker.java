package src.main.stat.statMethod;

import src.main.entity.pieces.Piece;
import src.main.stat.statVar.StatField;

public class InputChecker {
    public static boolean newGriddimentionChecker(int newRow, int newCol) {
        if (newCol < StatField.FIRST || newRow < StatField.FIRST || newRow > StatField.EIGHTS
                || newCol > StatField.EIGHTS) {
            return false;
        }
        return true;
    }

    public static boolean SameColourChecker(Piece piece, Piece piece2) {
        if (piece == null || piece2 == null) {
            return false;
        }
        if (piece.isWhite() && piece2.isWhite()) {
            return true;
        } else if (!piece.isWhite() && !piece2.isWhite()) {
            return true;
        }
        return false;
    }
}
