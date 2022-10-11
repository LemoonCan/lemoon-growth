package designpattern.structured.flyweight.factory;

import designpattern.structured.flyweight.Color;

/**
 * @author lee
 * @date 2022/10/11
 */
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }


}
