package jvm.methodinvoke;

/**
 * 静态分派
 * @author lee
 * @since 2022/7/21
 */
public class StaticDispatch {
    public static void sayHello(Human guy){
        System.out.println("Hello human!");
    }
    public static void sayHello(Man guy){
        System.out.println("Hello man!");
    }
    public static void sayHello(Woman guy){
        System.out.println("Hello Woman!");
    }

    public static void main(String[] args) {
        //静态类型是 Human，实际类型是 Man
        Human man = new Man();
        Human woman = new Woman();
        sayHello(man);
        sayHello(woman);

        Man realMan = new Man();
        Woman realWoman = new Woman();
        sayHello(realMan);
        sayHello(realWoman);
    }
}