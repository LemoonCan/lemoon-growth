package javabasic.multithread.communication.waitnotify;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lee
 * @since 3/11/21
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            System.out.println("children thread begin park!");
            LockSupport.park();
            System.out.println("children thread end park!");
        });
        t.start();
        Thread.sleep(10000);
        System.out.println("main thread begin unpark!");
        LockSupport.unpark(t);

        SynchronousQueue synchronousQueue = new SynchronousQueue<Runnable>();
        synchronousQueue.offer(t);
        new Thread();
    }
}
