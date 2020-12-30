package main.javabasic.generics.array;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author lee
 * @date 2020-09-17
 */
public class GenericArrayWithTypeToken<T> {
    public static void main(String[] args) {
        GenericArrayWithTypeToken<Integer> gai = new GenericArrayWithTypeToken<>(Integer.class, 10);
        Integer[] ia = gai.rep();
        ia[0] = 0;
        System.out.println(Arrays.toString(ia));
    }

    private T[] array;

    public GenericArrayWithTypeToken(Class<T> type, int sz) {
        array = (T[]) Array.newInstance(type, sz);
    }

    public void put(int index, T item) {
        array[index] = item;
    }

    public T get(int index) {
        return array[index];
    }

    public T[] rep() {
        return array;
    }
}
