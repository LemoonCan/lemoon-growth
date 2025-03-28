package javabasic.container.map;

import java.util.EnumMap;

import static javabasic.container.map.EnumMapTest.Size.LARGE;
import static javabasic.container.map.EnumMapTest.Size.MEDIUM;
import static javabasic.container.map.EnumMapTest.Size.SMALL;

/**
 * @author lee
 * @since 6/28/21
 */
public class EnumMapTest {
    public static void main(String[] args) {
        EnumMap<Size,Integer> enumMap = new EnumMap(Size.class);
        enumMap.put(SMALL,1);
        enumMap.put(MEDIUM,1);
        enumMap.put(LARGE,1);
    }

    enum Size{
        SMALL,
        MEDIUM,
        LARGE
    }

    enum Color{
        RED
    }
}
