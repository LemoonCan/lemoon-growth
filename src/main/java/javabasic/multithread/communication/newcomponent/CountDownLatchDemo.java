package javabasic.multithread.communication.newcomponent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-12-07
 * CountDownLatch 只控制触发通知的次数
 * CountDownLatch(触发次数)（来多次，往一次）
 */
public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);

        for (int i = 0; i < 10; i++) {
            exec.execute(new WaitingTask(latch));
        }
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new TaskPortion(latch));
        }
        System.out.println("Lauched all tasks");
        exec.shutdown();
    }
}

class TaskPortion implements Runnable{
    private static Random rand = new Random(47);
    private static int counter = 0;
    private final int id = counter++;

    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
        }catch (InterruptedException e){

        }
    }

    public void doWork()throws InterruptedException{
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this+"completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d ",id);
    }
}

class WaitingTask implements Runnable{
    private final CountDownLatch latch;
    private static int counter = 0;
    private final int id= counter++;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            latch.await();
            System.out.println("Latch barrier passed for "+this);
        }catch (InterruptedException e){
            System.out.println(this+" interrupted");
        }
    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d ",id);
    }
}

