package javabasic.generics.bounds;

import java.awt.*;
import java.awt.Dimension;

/**
 * @author lee
 * @date 2020-09-18
 */
public class InheritBounds {
    public static void main(String[] args) {
        Solid2<Bounded> solid2 = new Solid2<>(new Bounded());
        solid2.color();
        solid2.getY();
        solid2.weight();
    }
}

class HoldItem<T> {
    protected T item;

    public HoldItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}

class Colored2<T extends HasColor> extends HoldItem<T> {

    public Colored2(T item) {
        super(item);
    }

    Color color() {
        return item.getColor();
    }
}

class ColoredDimension2<T extends java.awt.Dimension & HasColor> extends Colored2<T> {

    public ColoredDimension2(T item) {
        super(item);
    }

    public int getX() {
        return item.x;
    }

    public int getY() {
        return item.y;
    }

    public int getZ() {
        return item.z;
    }
}

class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {

    public Solid2(T item) {
        super(item);
    }

    int weight() {
        return item.weight();
    }
}

