package javabasic.generics.potential;

import javabasic.generics.Generator;
import javabasic.generics.coffee.Coffee;
import javabasic.generics.coffee.Latte;
import javabasic.generics.coffee.Mocha;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lee
 * @date 2020-09-22
 */
public class Fill2 {
    public static <T> void fill(Addable<T> addable, Class<? extends T> classToken, int size) {
        try {
            for (int i = 0; i < size; i++) {
                addable.add(classToken.newInstance());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> void fill(Addable<T> addable, Generator<T> generator, int size) {
        for (int i = 0; i < size; i++) {
            addable.add(generator.next());
        }
    }
}

interface Addable<T> {
    void add(T t);
}

class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;

    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }

    @Override
    public void add(T t) {
        c.add(t);
    }
}

class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<>(c);
    }
}

class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
}

class Fill2Test {
    public static void main(String[] args) {
        List<Coffee> carrier = new ArrayList<>();
        Fill2.fill(new AddableCollectionAdapter<>(carrier), Coffee.class, 3);
        Fill2.fill(new AddableCollectionAdapter<>(carrier), Latte.class, 2);
        for (Coffee c:carrier) {
            System.out.println(c);
        }
        System.out.println("-------------------------------------");
        AddableSimpleQueue<Coffee> coffeeQueue = new AddableSimpleQueue<>();
        Fill2.fill(coffeeQueue, Mocha.class, 4);
        Fill2.fill(coffeeQueue, Latte.class, 1);
        for (Coffee c:coffeeQueue) {
            System.out.println(c);
        }
    }
}