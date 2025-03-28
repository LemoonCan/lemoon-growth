package designpattern.behavior.iterator;

import java.util.ArrayList;

/**
 * @author lee
 * @since 2022/10/19
 */
public class ArrayIterator<E> implements Iterator<E> {
    private ArrayList<E> list;
    int cur = 0;

    public ArrayIterator(ArrayList<E> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return cur < list.size();
    }

    @Override
    public E next() {
        return list.get(cur++);
    }
}
