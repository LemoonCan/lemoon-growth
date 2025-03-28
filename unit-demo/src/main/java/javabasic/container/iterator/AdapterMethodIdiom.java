package javabasic.container.iterator;

import java.util.Arrays;

/**
 * @author lee
 * @since 2020-11-02
 */
public class AdapterMethodIdiom {
    public static void main(String[] args) {
        //Arrays.asList(n[]) 会使用原数组作为其物理实现
        ReversibleArrayList<String> ral = new ReversibleArrayList<>(Arrays.asList("To be or not to be".split(" ")));
        for (String s : ral) {
            System.out.println(s + " ");
        }
        for (String s : ral.reversed()) {
            System.out.println(s + " ");
        }
    }
}
