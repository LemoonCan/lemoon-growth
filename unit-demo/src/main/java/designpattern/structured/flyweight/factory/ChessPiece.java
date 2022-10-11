package designpattern.structured.flyweight.factory;

/**
 * @author lee
 * @date 2022/10/11
 */
public class ChessPiece {
    private ChessPieceUnit chessPieceUnit;
    private int px;
    private int py;

    public ChessPiece(ChessPieceUnit chessPieceUnit, int px, int py) {
        this.chessPieceUnit = chessPieceUnit;
        this.px = px;
        this.py = py;
    }
}
