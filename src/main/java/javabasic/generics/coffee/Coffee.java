package javabasic.generics.coffee;

/**
 * @author lee
 * @date 2020-09-11
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
