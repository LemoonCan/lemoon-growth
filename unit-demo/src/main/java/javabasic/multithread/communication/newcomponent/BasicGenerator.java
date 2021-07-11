package javabasic.multithread.communication.newcomponent;

import javabasic.dynamic.generics.CountedObject;
import javabasic.dynamic.generics.Generator;

import java.util.Iterator;

/**
 * @author lee
 * @date 2020-09-11
 */
public class BasicGenerator<T> implements Generator<T>, Iterable<T> {
    private Class<T> type;
    private int size;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    public BasicGenerator(Class<T> type, int size) {
        this.type = type;
        this.size = size;
    }

    @Override
    public T next() {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<>(type);
    }

    class BasicIterator implements Iterator {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public T next() {
            count--;
            return BasicGenerator.this.next();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator();
    }

    public static void main(String[] args) {
        BasicGenerator<CountedObject> gen = new BasicGenerator(CountedObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }

        //类型擦除
        for (Object c : new BasicGenerator(CountedObject.class, 5)) {
            System.out.println(c);
        }
    }
}
