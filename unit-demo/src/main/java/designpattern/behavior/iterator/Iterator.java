package designpattern.behavior.iterator;

/**
 * @author lee
 * @since 2022/10/19
 */
public interface Iterator<E> {
    boolean hasNext();
    E next();
}
