package javabasic.dynamic.reflect.type;

import console.ColorfulPrintln;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 6/1/21
 */
public class WildcardTypeTest {
    List<?> x;
    public void test(List<? extends Number> x){
    }

    public void testSuper(List<? super Number> x){
    }

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        Class clazz = WildcardTypeTest.class;

        Field xField = clazz.getDeclaredField("x");
        System.out.println();
        ColorfulPrintln.colorfulBack("List<?>");
        printWildCardType(((ParameterizedType) xField.getGenericType()));


        Method method = clazz.getMethod("test",List.class);
        Type[] types = method.getGenericParameterTypes();
        System.out.println();
        ColorfulPrintln.colorfulBack("List<? extends Number>");
        System.out.println(types[0] instanceof ParameterizedType);
        printWildCardType((ParameterizedType) types[0]);

        method = clazz.getMethod("testSuper",List.class);
        types = method.getGenericParameterTypes();
        System.out.println();
        ColorfulPrintln.colorfulBack("List<? super Number>");
        System.out.println(types[0] instanceof ParameterizedType);
        printWildCardType((ParameterizedType) types[0]);

    }

    public static void printWildCardType(ParameterizedType parameterizedType){
        System.out.println(Arrays.toString(parameterizedType.getActualTypeArguments()));
        System.out.println("是否为wildcardType类型："+(parameterizedType.getActualTypeArguments()[0] instanceof WildcardType));
        WildcardType wildcardType = (WildcardType)parameterizedType.getActualTypeArguments()[0];
        System.out.println("上界："+Arrays.toString(wildcardType.getUpperBounds()));
        System.out.println("下界："+Arrays.toString(wildcardType.getLowerBounds()));
    }
}
