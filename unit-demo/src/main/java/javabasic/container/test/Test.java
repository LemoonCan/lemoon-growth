package javabasic.container.test;

/**
 * @author lee
 * @since 2020-09-24
 */
public abstract class Test<C> {
    String name;
    public Test(String name){
        this.name = name;
    }

    abstract int test(C container,TestParam pt);
}
