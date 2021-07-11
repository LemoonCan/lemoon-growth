package javabasic.dynamic.reflect;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lee
 * @date 5/30/21
 * 与 member类似
 * 调用泛型方法，参数类型设置为泛型的上界
 */
public class JudgeAction {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Lemoon lemoon = new Lemoon();
        Class clazz = lemoon.getClass();
        //获取当前类所有方法
        System.out.println(clazz.getDeclaredMethods().length);
        System.out.println(JSON.toJSONString(clazz.getDeclaredMethods()));
        //获取当前类及父类的 public方法
        System.out.println(clazz.getMethods().length);
        System.out.println(JSON.toJSONString(clazz.getMethods()));

        Method method = clazz.getDeclaredMethod("fillCan",String.class);
        method.setAccessible(true);
        method.invoke(lemoon,"water");
        Field field = clazz.getDeclaredField("can");
        field.setAccessible(true);
        System.out.println(field.get(lemoon));

        //returnGeneric paramGeneric exceptionGeneric
        clazz = GenericLemoon.class;
        //调用泛型方法
        method = clazz.getMethod("xy", Object.class, Number.class);
        System.out.println("泛型参数："+Arrays.toString(method.getGenericParameterTypes()));
        System.out.println("泛型返回值："+method.getGenericReturnType());
        System.out.println("声明的泛型异常："+Arrays.toString(method.getGenericExceptionTypes()));
        System.out.println(Arrays.toString(method.getExceptionTypes()));
    }
}
