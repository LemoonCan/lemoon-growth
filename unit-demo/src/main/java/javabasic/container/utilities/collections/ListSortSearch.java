package javabasic.container.utilities.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * @author lee
 * @since 2020-09-27
 */
public class ListSortSearch {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Utilities.list);
        list.addAll(Utilities.list);
        System.out.println(list);
        Collections.shuffle(list, new Random(47));
        System.out.println("Shuffled: "+list);

        ListIterator<String> it = list.listIterator(10);
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        System.out.println("Trimmed: " + list);
        Collections.sort(list);
        System.out.println("Sorted: " + list);
        String key = list.get(7);
        int index = Collections.binarySearch(list, key);
        System.out.println("Location of " + key + " is " + index + ", list.get(" + index + ") = " + list.get(index));
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Case-insensitive sorted: " + list);
        key = list.get(7);
        index = Collections.binarySearch(list, key, String.CASE_INSENSITIVE_ORDER);
        System.out.println("Location of " + key + " is " + index + ", list.get(" + index + ") = " + list.get(index));
    }
}
