package main.javabasic.container.collection.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lee
 * @date 2020-07-06
 */
public class ArrayListTest {
    private static List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add("1");
        list.add("2");
        list.add("3");

        final Iterator<String> it = list.iterator();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(() ->
            {
                while (it.hasNext()) {
                    System.err.println(it.next());
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            service.execute(() -> list.add("愚公"));
        }
    }
}
