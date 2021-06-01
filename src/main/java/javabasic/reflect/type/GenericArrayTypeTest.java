package javabasic.reflect.type;

import console.ColorfulPrintln;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;

/**
 * @author lee
 * @date 6/1/21
 */
public class GenericArrayTypeTest<T> {
    List<String>[] genClassArray;
    T[] genArray;
    List<T>[] tListArray;

    public static void main(String[] args) throws NoSuchFieldException {
        Class clazz = GenericArrayTypeTest.class;

        System.out.println();
        ColorfulPrintln.colorfulBack("List<String>[]");
        Field genClassArrayField = clazz.getDeclaredField("genClassArray");
        System.out.println(genClassArrayField.getGenericType().getClass().getName());
        System.out.println("数组内的元素类型：" + ((GenericArrayType) genClassArrayField.getGenericType()).getGenericComponentType());
        System.out.println("数组内的元素是否为参数化类型：" + (((GenericArrayType) genClassArrayField.getGenericType()).getGenericComponentType() instanceof ParameterizedType));


        System.out.println();
        ColorfulPrintln.colorfulBack("T[]");
        Field genArrayField = clazz.getDeclaredField("genArray");
        System.out.println(genArrayField.getGenericType().getClass().getName());
        System.out.println("数组内的元素类型：" + ((GenericArrayType) genArrayField.getGenericType()).getGenericComponentType());
        System.out.println("数组内的元素是否为参数化类型：" + (((GenericArrayType) genArrayField.getGenericType()).getGenericComponentType() instanceof ParameterizedType));
        System.out.println("数组内的元素是否为类型参数：" + (((GenericArrayType) genArrayField.getGenericType()).getGenericComponentType() instanceof TypeVariable));

        System.out.println();
        ColorfulPrintln.colorfulBack("List<T>[]");
        Field tListArrayField = clazz.getDeclaredField("tListArray");
        System.out.println(tListArrayField.getGenericType().getClass().getName());
        Type componentType = ((GenericArrayType) tListArrayField.getGenericType()).getGenericComponentType();
        System.out.println("数组内的元素类型：" + componentType);
        System.out.println("数组内的元素是否为参数化类型：" + (componentType instanceof ParameterizedType));
        System.out.println("实际的参数化类型：" + (((ParameterizedType) componentType).getActualTypeArguments()[0] instanceof TypeVariable));
        System.out.println("类或接口类型：" + ((ParameterizedType) componentType).getRawType());
        System.out.println("ownerType：" + ((ParameterizedType) componentType).getOwnerType());
    }
}
