package jvm.load.passive;

/**
 * @author lee
 * @date 2022/3/7
 */
public class SuperClass {
    static {
        System.out.println("Super Class init");
    }
    static int value=123;
}
