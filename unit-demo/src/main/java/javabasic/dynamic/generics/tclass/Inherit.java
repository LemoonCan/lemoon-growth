package javabasic.dynamic.generics.tclass;

import console.ColorfulPrintln;
import java.lang.reflect.ParameterizedType;

/**
 * @author lee
 * @since 5/24/21
 *
 * 子类泛型声明不受父类影响
 * extends 后描述的父类的泛型声明，表示父类的泛型应该在子类中如何表现
 * 不声明：变成擦除后的类型(Object或者泛型继承的类型)，同时继承的父类会变成Class类型
 * 声明：按声明来，同时继承的父类还是ParameterizedType
 *
 */
public class Inherit {
    public static void main(String[] args) {
        Children1 children1 = new Children1(1,2);
        ColorfulPrintln.colorfulBack("children1");
        System.out.println(children1.getClass().getGenericSuperclass() instanceof ParameterizedType);


        Children2<Float> children2 = new Children2(1,2);
        ColorfulPrintln.colorfulBack("children2");
        System.out.println(children2.getClass().getGenericSuperclass() instanceof ParameterizedType);

        ColorfulPrintln.colorfulBack("children3");
        Children3<Float> children3 = new Children3<>("child3",1);
        children3.setT(1.0f);
        System.out.println(children3.getT());
        System.out.println(children3.getClass().getGenericSuperclass() instanceof ParameterizedType);

        ColorfulPrintln.colorfulBack("children4");
        Children4<Float,Double> children4= new Children4<>(2.0f,2);
        children4.setS(4.0d);
        System.out.println(children4.getS());
        System.out.println(children4.toString());
        System.out.println(children4.getClass().getGenericSuperclass() instanceof ParameterizedType);

    }

}

class Basic<T,R> {
    protected T t;
    protected R r;

    public Basic(T t, R r) {
        this.t = t;
        this.r = r;
    }

    @Override
    public String toString() {
        return "t="+t+"&r="+r;
    }
}

/**
 * 不声明：变成擦除后的类型(Object或者泛型继承的类型)
 */
class Children1 extends Basic {
    public Children1(Object o, Object o2) {
        super(o, o2);
    }
}

class Children2<T> extends Basic {
    public Children2(Object o, Object o2) {
        super(o, o2);
    }
}

/**
 * 声明：按声明来
 * @param <T>
 */
class Children3<T> extends Basic<String, Integer> {
    private T t;

    public Children3(String string, Integer integer) {
        super(string, integer);
    }

    public Children3(String s, Integer integer, T t) {
        super(s, integer);
        this.t = t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }
}

class Children4<T, S extends Number> extends Basic<T, Integer> {
    private S s;
    public Children4(T t, Integer integer) {
        super(t, integer);
    }

    public S getS() {
        return s;
    }

    public void setS(S s) {
        this.s = s;
    }
}

