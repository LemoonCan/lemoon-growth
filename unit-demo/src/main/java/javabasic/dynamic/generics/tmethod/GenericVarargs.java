package javabasic.dynamic.generics.tmethod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lee
 * @since 2020-09-11
 */
public class GenericVarargs {
    public static <T> List<T> makeList(T... args){
        List<T> result = new ArrayList<>();
        for (T item:args) {
            result.add(item);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A","B","C");
        System.out.println(ls);
        ls = makeList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}
