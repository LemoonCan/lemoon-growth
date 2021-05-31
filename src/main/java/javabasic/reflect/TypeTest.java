package javabasic.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 5/31/21
 */
public class TypeTest <T> {
    List<String> list;
    Map<String,String> map;
    String[] strings;
    List<String>[] lists;
    T t;
    T[] ts;

    public static void main(String[] args) throws NoSuchFieldException {
        List<String> x = new ArrayList<>();
        System.out.println(x.getClass().getName());
        System.out.println(x.getClass().getTypeName());

        System.out.println("List<String>=====================");
        Field listField = TypeTest.class.getDeclaredField("list");
        System.out.println(listField.getClass().getName());
        System.out.println(listField.getGenericType().getClass().getName());

        System.out.println("Map<String,String>=====================");
        Field mapField = TypeTest.class.getDeclaredField("map");
        System.out.println(mapField.getClass().getName());
        System.out.println(mapField.getGenericType().getClass().getName());

        System.out.println("String[]=====================");
        Field stringsField = TypeTest.class.getDeclaredField("strings");
        System.out.println(stringsField.getClass().getName());
        System.out.println(stringsField.getGenericType().getClass().getName());

        System.out.println("List<String>[]=====================");
        Field listsField = TypeTest.class.getDeclaredField("lists");
        System.out.println(listsField.getClass().getName());
        System.out.println(listsField.getGenericType().getClass().getName());

        System.out.println("T=====================");
        Field tField = TypeTest.class.getDeclaredField("t");
        System.out.println(tField.getClass().getName());
        System.out.println(tField.getGenericType().getClass().getName());

        System.out.println("T[]=====================");
        Field tsField = TypeTest.class.getDeclaredField("ts");
        System.out.println(tsField.getClass().getName());
        System.out.println(tsField.getGenericType().getClass().getName());

    }
}
