package main.javabasic.container.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author lee
 * @date 2020-11-02
 */
public class ReversibleArrayList<T> extends ArrayList<T> {
    public ReversibleArrayList(Collection<? extends T> c) {
        super(c);
    }

    public Iterable<T> reversed() {
        return () -> new Iterator<T>() {
            int current = size() - 1;

            @Override
            public boolean hasNext() {
                return current > -1;
            }

            @Override
            public T next() {
                return get(current--);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
