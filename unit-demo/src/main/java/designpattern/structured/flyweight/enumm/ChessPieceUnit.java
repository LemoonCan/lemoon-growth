package designpattern.structured.flyweight.enumm;

import designpattern.structured.flyweight.Color;

/**
 * @author lee
 * @date 2022/10/11
 */
public enum ChessPieceUnit {
    /**
     * 车
     */
    R_Rooks_1(0, "车", Color.RED),
    R_Rooks_2(1, "车", Color.RED),
    R_Knights_1(2, "马", Color.RED),
    R_Knights_2(3, "马", Color.RED),
    R_Cannons_1(4, "炮", Color.RED),
    R_Cannons_2(5, "炮", Color.RED),
    R_Elephants_1(6, "象", Color.RED),
    R_Elephants_2(7, "象", Color.RED),
    R_Mandarins_1(8, "士", Color.RED),
    R_Mandarins_2(9, "士", Color.RED),
    R_King(10, "将", Color.RED),
    R_Pawns_1(11, "卒", Color.RED),
    R_Pawns_2(12, "卒", Color.RED),
    R_Pawns_3(13, "卒", Color.RED),
    R_Pawns_4(14, "卒", Color.RED),
    R_Pawns_5(15, "卒", Color.RED),

    B_Rooks_1(16, "车", Color.BLACK),
    B_Rooks_2(17, "车", Color.BLACK),
    B_Knights_1(18, "马", Color.BLACK),
    B_Knights_2(19, "马", Color.BLACK),
    B_Cannons_1(20, "炮", Color.BLACK),
    B_Cannons_2(21, "炮", Color.BLACK),
    B_Elephants_1(22, "象", Color.BLACK),
    B_Elephants_2(23, "象", Color.BLACK),
    B_Mandarins_1(24, "士", Color.BLACK),
    B_Mandarins_2(25, "士", Color.BLACK),
    B_King(26, "将", Color.BLACK),
    B_Pawns_1(27, "卒", Color.BLACK),
    B_Pawns_2(28, "卒", Color.BLACK),
    B_Pawns_3(29, "卒", Color.BLACK),
    B_Pawns_4(30, "卒", Color.BLACK),
    B_Pawns_5(31, "卒", Color.BLACK),
    ;

    private int id;
    private String text;
    private Color color;

    ChessPieceUnit(int id, String text, Color color) {
        this.id = id;
        this.text = text;
        this.color = color;
    }
}
