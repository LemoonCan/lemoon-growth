package jvm.load.passive;

/**
 * @author lee
 * @since 2022/3/7
 */
public class SubClass extends SuperClass{
    static {
        System.out.println("Sub Class init");
    }
}
