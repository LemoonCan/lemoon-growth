package javabasic.dynamic.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lee
 * @date 5/27/21
 *
 * 运行时构造任意一个类的对象
 */
public class BuildObject {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //1.class.newInstance 只能调用public无参构造方法
        Class clazz = Class.forName("javabasic.dynamic.reflect.Lemoon");
        Object o = Class.forName("javabasic.dynamic.reflect.Lemoon").newInstance();
        System.out.println(o instanceof Lemoon);

        //2.Constructor
        //getConstructors
        Constructor cons = clazz.getConstructor(String.class,String.class);
        Object o2 = cons.newInstance("can-o","moon-o");
        System.out.println(o2 instanceof Lemoon);
    }
}
