package javabasic.generics.methods;

import javabasic.generics.Generator;
import javabasic.generics.coffee.Coffee;
import javabasic.generics.coffee.CoffeeGenerator;
import javabasic.generics.Fibonacci;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author lee
 * @date 2020-09-11
 */
public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffees = fill(new ArrayList<>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffees) {
            System.out.println(c);
        }
        Collection<Integer> fNumbers = fill(new ArrayList<>(), new Fibonacci(), 12);
        for (int i : fNumbers) {
            System.out.print(i + ", ");
        }
    }
}
