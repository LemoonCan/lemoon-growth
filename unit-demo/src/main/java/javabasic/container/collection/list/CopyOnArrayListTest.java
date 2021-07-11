package javabasic.container.collection.list;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lee
 * @date 2020-07-06
 */
public class CopyOnArrayListTest {
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList();
    public static void main(String[] args) {
        list.add("1");
        list.add("2");
        list.add("3");
        list.get(1);

        final Iterator<String> it = list.iterator();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(() ->
            {
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
            });
        }

        for (int i = 0; i < 10; i++) {
            service.execute(() -> list.add("愚公"));
        }
    }
}
