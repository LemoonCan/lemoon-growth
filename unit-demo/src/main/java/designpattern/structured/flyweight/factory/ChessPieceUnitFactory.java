package designpattern.structured.flyweight.factory;

import designpattern.structured.flyweight.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lee
 * @date 2022/10/11
 */
public class ChessPieceUnitFactory {
    private static Map<Integer,ChessPieceUnit> chessPieceUnits = new HashMap<>(32,1);
    static {
        chessPieceUnits.put(0,new ChessPieceUnit(0,"車", Color.RED));
        chessPieceUnits.put(1,new ChessPieceUnit(1,"車", Color.RED));
        chessPieceUnits.put(2,new ChessPieceUnit(2,"馬", Color.RED));
        //...
    }

    public static ChessPieceUnit getById(Integer id){
        return chessPieceUnits.get(id);
    }
}
