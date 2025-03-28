package jvm.load.passive;

/**
 * @author lee
 * @since 2022/3/7
 */
public class SuperClass {
    static {
        System.out.println("Super Class init");
    }
    static int value=123;
}
