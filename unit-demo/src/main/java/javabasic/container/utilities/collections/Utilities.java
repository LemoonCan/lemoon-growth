package javabasic.container.utilities.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.Vector;

/**
 * @author lee
 * @date 2020-09-24
 */
public class Utilities {
    static List<String> list = Arrays.asList("one Two three Four five six one".split(" "));

    public static void main(String[] args) {
        System.out.println(list);
        System.out.println("'list' disjoint (Four)?: " + Collections.disjoint(list, Collections.singletonList("Four")));
        System.out.println("max: " + Collections.max(list));
        System.out.println("min: " + Collections.min(list));
        System.out.println("max w/ comparator: " + Collections.max(list, String.CASE_INSENSITIVE_ORDER));
        List<String> subList = Arrays.asList("Four five six".split(" "));
        //sublist 第一次出现的位置
        System.out.println("indexOfSubList: " + Collections.indexOfSubList(list, subList));
        //sublist 最后一次出现的位置
        System.out.println("lastIndexOfSubList: " + Collections.lastIndexOfSubList(list, subList));
        Collections.replaceAll(list, "one", "Yo");
        System.out.println("replaceAll: " + list);
        //元素向后移动x个位置
        Collections.rotate(list, 3);
        System.out.println("rotate: " + list);
        List<String> source = Arrays.asList("in the matrix".split(" "));
        Collections.copy(list, source);
        System.out.println("copy: " + list);
        Collections.swap(list, 0, list.size() - 1);
        System.out.println("swap: " + list);
        //随机改变指定列表的顺序
        Collections.shuffle(list, new Random(47));
        System.out.println("shuffled: " + list);
        Collections.fill(list, "pop");
        System.out.println("fill:" + list);
        System.out.println("frequency of pop: " + Collections.frequency(list, "pop"));
        List<String> dups = Collections.nCopies(3, "snap");
        System.out.println("dups: " + dups);
        //比较两个集合是否无交集
        System.out.println("'list' disjoint 'dups'?: " + Collections.disjoint(list, dups));
        Enumeration<String> e = Collections.enumeration(dups);
        System.out.println("enumeration: " + e);
        Vector<String> v = new Vector<>();
        while (e.hasMoreElements()) {
            v.add(e.nextElement());
        }
        ArrayList<String> arrayList = Collections.list(v.elements());
        System.out.println("arrayList: " + arrayList);
    }
}
