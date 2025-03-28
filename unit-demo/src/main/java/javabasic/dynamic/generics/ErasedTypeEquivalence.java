package javabasic.dynamic.generics;

import java.util.ArrayList;

/**
 * @author lee
 * @since 2020-09-15
 */
public class ErasedTypeEquivalence {
    public static void main(String[] args) {
        Class c1 = new ArrayList<String>().getClass();
        Class c2 = new ArrayList<Integer>().getClass();
        System.out.println(c1==c2);
    }
}
