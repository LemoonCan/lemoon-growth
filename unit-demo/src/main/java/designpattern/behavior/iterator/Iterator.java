package designpattern.behavior.iterator;

/**
 * @author lee
 * @date 2022/10/19
 */
public interface Iterator<E> {
    boolean hasNext();
    E next();
}
