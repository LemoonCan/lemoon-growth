package javabasic.dynamic.reflect.type;

import console.ColorfulPrintln;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * @author lee
 * @date 6/1/21
 */
public class TypeVariableTest<T, Y extends Number> {
    T t;
    Y y;

    public static void main(String[] args) throws NoSuchFieldException {
        Class clazz = TypeVariableTest.class;

        System.out.println();
        ColorfulPrintln.colorfulBack("T");
        Field tField = clazz.getDeclaredField("t");
        pringTypeVariable(tField.getGenericType());

        System.out.println();
        ColorfulPrintln.colorfulBack("Y");
        Field yField = clazz.getDeclaredField("y");
        pringTypeVariable(yField.getGenericType());
    }

    public static void pringTypeVariable(Type type){
        System.out.println(type.getClass().getName());
        System.out.println("边界：" + Arrays.toString(((TypeVariable) type).getBounds()));
        System.out.println("泛型声明类：" + ((TypeVariable) type).getGenericDeclaration());
    }
}
