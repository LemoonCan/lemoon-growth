package javabasic.container.map;

import java.util.TreeMap;

/**
 * @author lee
 * @since 6/23/21
 */
public class TreeMapTest {
    public static void main(String[] args) {
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(2,"A");
        map.put(5,"Y");
        map.put(4,"P");
        map.put(1,"H");
        map.put(3,"P");
        map.put(6,"!");


        map.forEach((key,value)->
            System.out.println(key+" "+value)
        );

        map.remove(6);
    }
}
