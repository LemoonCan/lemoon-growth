package javabasic.reflect;

import java.lang.reflect.Constructor;

/**
 * @author lee
 * @date 5/27/21
 *
 * 运行时构造任意一个类的对象
 */
public class BuildObject {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //1.class.newInstance
        Class clazz = Class.forName("javabasic.reflect.Lemoon");
        Object o = Class.forName("javabasic.reflect.Lemoon").newInstance();
        System.out.println(o instanceof Lemoon);

        //2.Constructor
        Constructor<?> cons[] = clazz.getConstructors();
        for (Constructor constructor:cons) {

        }
    }
}
