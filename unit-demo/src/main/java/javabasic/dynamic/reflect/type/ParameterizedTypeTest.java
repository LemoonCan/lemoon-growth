package javabasic.dynamic.reflect.type;

import console.ColorfulPrintln;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @since 6/1/21
 */
public class ParameterizedTypeTest {
    List<String> list;
    Map<String, Integer> map;
    List<?> markList;
    List<? extends Number> markExtendsList;

    public static void main(String[] args) throws NoSuchFieldException {
        Class clazz = ParameterizedTypeTest.class;
        System.out.println(Arrays.toString(clazz.getTypeParameters()));

        System.out.println();
        ColorfulPrintln.colorfulBack("List<String>");
        Field listField = clazz.getDeclaredField("list");
        System.out.println(listField.getGenericType().getClass().getName());


        System.out.println();
        ColorfulPrintln.colorfulBack("Map<String,String>");
        Field mapField = clazz.getDeclaredField("map");
        System.out.println(mapField.getGenericType().getClass().getName());
        printParamterizedType((ParameterizedType) mapField.getGenericType());


        System.out.println();
        ColorfulPrintln.colorfulBack("List<?>");
        Field markListField = clazz.getDeclaredField("markList");
        System.out.println(markListField.getGenericType().getClass().getName());
        printParamterizedType((ParameterizedType) markListField.getGenericType());

        System.out.println();
        ColorfulPrintln.colorfulBack("List<? extends Number>");
        Field markExtendsListField = clazz.getDeclaredField("markExtendsList");
        System.out.println(markExtendsListField.getGenericType().getClass().getName());
        printParamterizedType((ParameterizedType) markExtendsListField.getGenericType());
    }

    public static void printParamterizedType(ParameterizedType parameterizedType){
        System.out.println("类或接口类型:"+parameterizedType.getRawType());
        System.out.println(parameterizedType.getOwnerType());
        System.out.println("实际的参数类型：" + Arrays.toString(parameterizedType.getActualTypeArguments()));
    }
}
