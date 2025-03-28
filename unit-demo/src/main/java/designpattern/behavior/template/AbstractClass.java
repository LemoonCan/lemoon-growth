package designpattern.behavior.template;

/**
 * @author lee
 * @since 2022/10/13
 */
public abstract class AbstractClass {
    public void doSth(){
        method1();
        method2();
    }

    public abstract void method1();
    public abstract void method2();
}
