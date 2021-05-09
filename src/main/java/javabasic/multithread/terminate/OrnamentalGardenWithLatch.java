package javabasic.multithread.terminate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-11-26
 */
public class OrnamentalGardenWithLatch {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<EntranceSeperate> entrances = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            EntranceSeperate entrance = new EntranceSeperate(i,latch);
            exec.execute(entrance);
            entrances.add(entrance);
        }
        exec.execute(new SumTask(entrances,latch));
        TimeUnit.SECONDS.sleep(3);
        EntranceSeperate.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS)) {
            System.out.println("Some tasks were not terminated!");
        }
        System.out.println("Total: " + EntranceSeperate.getTotalCount());
    }
}

class EntranceSeperate implements Runnable {
    private static Count count = new Count();
    private int number = 0;
    private final int id;
    private static volatile boolean canceled = false;

    private final CountDownLatch latch;

    public EntranceSeperate(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    public static void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            System.out.println(this + "Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("sleep interrupted");
            }
        }
        System.out.println("Stopping " + this);
        latch.countDown();
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }
}

class SumTask implements Runnable {
    private final List<EntranceSeperate> entranceSeperateList;
    private final CountDownLatch latch;

    public SumTask(List<EntranceSeperate> entranceSeperateList, CountDownLatch latch) {
        this.entranceSeperateList = entranceSeperateList;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            int sum = 0;
            for (EntranceSeperate entrance : entranceSeperateList) {
                sum += entrance.getValue();
            }
            System.out.println("Sum of Entrances:"+sum);
        }catch (InterruptedException e){
        }
    }
}
