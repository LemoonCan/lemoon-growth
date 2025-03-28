package javabasic.container.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author lee
 * @since 2020-09-23
 */
public class Unsupported {
    static void test(String msg, List<String> list) {
        System.out.println("=== " + msg + " ===");
        Collection<String> c = list;
        Collection<String> subList = list.subList(1, 8);
        Collection<String> c2 = new ArrayList<>(subList);

        try {
            c.retainAll(c2);
        } catch (Exception e) {
            System.out.println("retainAll():" + e);
        }
        try {
            c.retainAll(c2);
        } catch (Exception e) {
            System.out.println("retainAll():" + e);
        }
        try {
            c.removeAll(c2);
        } catch (Exception e) {
            System.out.println("removeAll():" + e);
        }
        try {
            c.clear();
        } catch (Exception e) {
            System.out.println("clear():" + e);
        }
        try {
            c.add("X");
        } catch (Exception e) {
            System.out.println("add():" + e);
        }
        try {
            c.addAll(c2);
        } catch (Exception e) {
            System.out.println("addAll():" + e);
        }
        try {
            c.remove("C");
        } catch (Exception e) {
            System.out.println("remove():" + e);
        }

        try {
            list.set(0, "X");
        } catch (Exception e) {
            System.out.println("List.set():" + e);
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
        test("Modifiable Copy", new ArrayList<>(list));
        //Arrays.asList 数组大小有变化的操作不支持
        test("Arrays.asList()", list);
        //Collections.unmodifiableList 任意修改操作都不支持
        test("unmodifiableList()", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
