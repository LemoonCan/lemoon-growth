package javabasic.multithread;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 1/4/21
 */
public class ActiveObjectDemo {
    private ExecutorService exec = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);


    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleeping interrupted");
        }
    }

    public Future<Integer> calculateInt(final int x, final int y) {
        return exec.submit(() -> {
            System.out.println("starting " + x + " + " + y);
            pause(500);
            return x + y;
        });
    }

    public Future<Float> calculateFloat(final float x, final float y) {
        return exec.submit(() -> {
            System.out.println("starting " + x + " + " + y);
            pause(500);
            return x + y;
        });
    }

    public void shutdown() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo a = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<>();

        for (float f = .0f; f < 1.0f; f += .2f) {
            results.add(a.calculateFloat(f, f));
        }

        for (int i = 0; i < 5; i++) {
            results.add(a.calculateInt(i, i));
        }

        System.out.println("all asynch calls made");
        while (results.size() > 0) {
            for (Future f : results) {
                if (f.isDone()) {
                    try {
                        System.out.println(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
            }
        }
        a.shutdown();
    }
}
