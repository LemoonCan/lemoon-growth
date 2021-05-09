package javabasic.container.array;

import java.util.Arrays;

/**
 * @author lee
 * @date 2020-09-10
 */
public class StringSorting {
    public static void main(String[] args) {
        String[] sa = {"a....","bbb"};
        Arrays.sort(sa);
        Arrays.sort(sa,String.CASE_INSENSITIVE_ORDER);
    }
}
