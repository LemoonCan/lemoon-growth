package javabasic.multithread.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author lee
 * @since 2020-12-07
 * 延迟执行队列
 */
public class DelayQueueDemo {
    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(rand.nextInt(5000)));
        }
        queue.add(new DelayedTask.EndSentinel(5000,exec));
        exec.execute(new DelayedTaskConsumer(queue));
    }
}

class DelayedTask implements Runnable, Delayed {
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;

    protected static List<DelayedTask> sequence = new ArrayList<>();

    public DelayedTask(int delayInMilliseconds) {
        this.delta = delayInMilliseconds;
        this.trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask)o;
        if(trigger<that.trigger) return -1;
        if(trigger>that.trigger) return 1;
        return 0;
    }

    @Override
    public void run() {
        System.out.println(this+" ");
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger-System.nanoTime(),NANOSECONDS);
    }

    public String summary(){
        return "("+id+":"+delta+")";
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]",delta)+" Task "+id;
    }

    public static class EndSentinel extends DelayedTask{
        private ExecutorService exec;

        public EndSentinel(int delayInMilliseconds,ExecutorService e) {
            super(delayInMilliseconds);
            this.exec = e;
        }

        @Override
        public void run() {
            for (DelayedTask pt:sequence) {
                System.out.print(pt.summary()+" ");
            }
            System.out.println();
            System.out.println(this+" calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class DelayedTaskConsumer implements Runnable{
    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try{
            while (!Thread.interrupted()){
                q.take().run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished DelayedTaskConsumer");
    }
}
