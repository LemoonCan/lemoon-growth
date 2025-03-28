package javabasic.container.collection;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author lee
 * @since 6/30/21
 */
public class MyCollection<E> extends AbstractCollection<E> {
    DynamicArray<E> darr;

    public MyCollection() {
        darr = new DynamicArray<>();
    }

    public MyCollection(Collection<E> c) {
        this();
        addAll(c);
    }

    @Override
    public Iterator iterator() {
        return new DynamicArrayIterator<>(darr);
    }

    @Override
    public int size() {
        return darr.size();
    }

    @Override
    public boolean add(E e) {
        darr.add(e);
        return true;
    }
}
