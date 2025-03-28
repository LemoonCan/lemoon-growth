package javabasic.dynamic.generics;

/**
 * @author lee
 * @since 2020-09-11
 */
public class CountedObject {
    private static long counter = 0;
    private final long id = counter++;
    public long id(){
        return id;
    }

    @Override
    public String toString() {
        return "CountedObject "+id;
    }
}
