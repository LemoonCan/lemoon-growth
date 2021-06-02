package javabasic.dynamic.generics.wildcard;

import java.awt.*;

/**
 * @author lee
 * @date 2020-09-18
 *
 * 泛型 extends 类需在接口前面
 * T extends Class1 & Interface1 & Interface2
 */
public class LetterMarkInherit {
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<>(new Bounded());
        System.out.println(solid.color());
        System.out.println(solid.getY());
        solid.weight();
    }
}

interface HasColor{
    Color getColor();
}

class Colored<T extends HasColor>{
    T item;

    public Colored(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public Color color(){
        return item.getColor();
    }
}

class Dimension{
    public int x,y,z;
}

/**
 * 泛型 extends 类需在接口前面
 * @param <T>
 */
class ColoredDimension<T extends Dimension & HasColor>{
    T item;

    public ColoredDimension(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public Color color(){
        return item.getColor();
    }

    public int getX(){
        return item.x;
    }

    public int getY(){
        return item.y;
    }

    public int getZ(){
        return item.z;
    }
}

interface Weight{
    int weight();
}

class Solid<T extends Dimension & HasColor & Weight>{
    T item;

    public Solid(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public Color color(){
        return item.getColor();
    }

    public int getX(){
        return item.x;
    }

    public int getY(){
        return item.y;
    }

    public int getZ(){
        return item.z;
    }

    public int weight(){
        return item.weight();
    }
}

class Bounded extends Dimension implements HasColor,Weight{

    @Override
    public Color getColor() {
        return null;
    }

    @Override
    public int weight() {
        return 0;
    }
}

