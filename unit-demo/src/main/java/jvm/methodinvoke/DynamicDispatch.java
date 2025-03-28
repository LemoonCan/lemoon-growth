package jvm.methodinvoke;

/**
 * 动态分派
 * @author lee
 * @since 2022/7/21
 */
public class DynamicDispatch {
    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
    }
}