package javabasic.dynamic.reflect.demo;

/**
 * @author lee
 * @since 2023/4/24
 */
public interface Rule<T extends AbstractDataBlock> {
    String identity();

    void verify(T block);
}
