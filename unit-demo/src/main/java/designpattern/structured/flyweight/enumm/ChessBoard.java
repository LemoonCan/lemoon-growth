package designpattern.structured.flyweight.enumm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2022/10/11
 */
public class ChessBoard {
    private Map<Integer, ChessPiece> chessPieces;

    public ChessBoard() {
        chessPieces = new HashMap<>(32,1);
        chessPieces.put(0,new ChessPiece(ChessPieceUnit.R_Rooks_1,0,0));
        chessPieces.put(2,new ChessPiece(ChessPieceUnit.R_Knights_1,0,1));
        //...
    }

    public ChessBoard(Map<Integer, ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public void move(int chessPieceId, int toX, int toY) {

    }
}
