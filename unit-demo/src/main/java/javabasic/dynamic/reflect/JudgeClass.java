package javabasic.dynamic.reflect;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * @author lee
 * @since 5/27/21
 *
 * 运行时判断一个对象所属的类
 */
public class JudgeClass {
    public static void main(String[] args) {
        //1.实例获取 class
        System.out.println(new Lemoon().getClass());

        //2.类获取 class
        System.out.println(Lemoon.class);

        //3.完全限定名获取 class
        try {
            System.out.println(Class.forName("javabasic.dynamic.reflect.Lemoon"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(LittleLemoon.class);
        System.out.println(LittleLemoon.class.getSuperclass());

        System.out.println(Arrays.toString(GenericLemoon.class.getTypeParameters()));
        System.out.println(GenericLemoon.class.getSuperclass());
        System.out.println(GenericLemoon.class.getGenericSuperclass() instanceof ParameterizedType);
        System.out.println(Arrays.toString(GenericLemoon.class.getGenericInterfaces()));

        System.out.println(GenericLittleLemoon.class.getSuperclass());
        System.out.println(GenericLittleLemoon.class.getGenericSuperclass() instanceof ParameterizedType);
    }
}
