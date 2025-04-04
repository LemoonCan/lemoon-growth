package javabasic.multithread.communication.newcomponent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 2020-12-09
 *
 * 信号灯 允许n个任务同时访问这个资源
 * Semaphore 控制n个任务访问某个资源，可加，可减 (来多次，往多次，有来的次数的最高限制)
 */
public class SemaphoreDemo {
    final static int SIZE = 25;

    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool = new Pool<>(Fat.class,SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckoutTask<>(pool));
        }
        System.out.println("All CheckoutTasks created");
        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.print(i+":main() thread checked out ");
            f.operation();
            list.add(f);
        }
        Future<?> blocked = exec.submit(() -> {
            try{
                pool.checkOut();
            }catch (InterruptedException e){
                System.out.println("checkOut() Interrupted");
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in "+list);
        for (Fat f:list) {
            pool.checkIn(f);
        }
        for (Fat f:list) {
            pool.checkIn(f);
        }
        exec.shutdown();
    }
}

class CheckoutTask<T> implements Runnable{
    private static int counter = 0;
    private final int id = counter++;

    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try{
            T item = pool.checkOut();
            System.out.println(this+"checked out "+item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this+"checking in "+item);
            pool.checkIn(item);
        }catch (InterruptedException e){
        }
    }

    @Override
    public String toString() {
        return "CheckoutTask "+id+" ";
    }
}
