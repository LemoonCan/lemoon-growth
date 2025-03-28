package javabasic.dynamic.reflect.type;

import console.ColorfulPrintln;
import javabasic.dynamic.reflect.Lemoon;

import java.lang.reflect.Field;

/**
 * @author lee
 * @since 6/1/21
 */
public class ClassTest {
    int a;
    Integer b;
    Lemoon lemoon;
    String[] strings;

    public static void main(String[] args) throws NoSuchFieldException {
        Class clazz = ClassTest.class;

        System.out.println();
        ColorfulPrintln.colorfulBack("String[]");
        Field stringsField = clazz.getDeclaredField("strings");
        System.out.println(stringsField.getGenericType().getClass().getName());

        System.out.println();
        ColorfulPrintln.colorfulBack("int");
        Field aField = clazz.getDeclaredField("a");
        System.out.println(aField.getGenericType().getClass().getName());

        System.out.println();
        ColorfulPrintln.colorfulBack("Integer");
        Field bField = clazz.getDeclaredField("b");
        System.out.println(bField.getGenericType().getClass().getName());

        System.out.println();
        ColorfulPrintln.colorfulBack("Lemoon");
        Field lemoonField = clazz.getDeclaredField("lemoon");
        System.out.println(lemoonField.getGenericType().getClass().getName());
    }
}
