package javabasic.container.collection;

/**
 * @author lee
 * @since 7/1/21
 */
public class DynamicArray<E> {
    private E[] elementData;
    private int size;

    public DynamicArray() {
        this.elementData = (E[]) new Object[16];
    }

    public E remove(int index) {
        E oldValue = get(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    public void add(E e) {
        elementData[size++] = e;
    }

    public void add(int index, E element) {
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    public E get(int index) {
        return elementData[index];
    }

    public int size() {
        return size;
    }
}
