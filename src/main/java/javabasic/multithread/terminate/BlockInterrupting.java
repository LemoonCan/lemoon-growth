package javabasic.multithread.terminate;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-11-26
 * cancel可中断sleep的调用及 ReentrantLock()的阻塞
 * 但对IO阻塞及synchronized阻塞无效
 * (IO资源关闭，可中断；NIO可中断)
 */
public class BlockInterrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException{
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupting "+r.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupt sent to "+r.getClass().getName());
    }

    public static void main(String[] args) throws Exception{
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        TimeUnit.SECONDS.sleep(3);
        System.out.println("Aborting with System.exit(0)");
        System.exit(0);
    }
}

class SleepBlocked implements Runnable{

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("SleepBlocked InterruptedException");
        }
        System.out.println("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable{
    private InputStream is;

    public IOBlocked(InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        try {
            System.out.println("Waiting for read():");
            is.read();
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrupted from blocked I/O");
            }else{
                throw new RuntimeException(e);
            }
        }
        System.out.println("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable{

    public synchronized void f(){
        while(true){
            Thread.yield();
        }
    }

    public SynchronizedBlocked() {
        new Thread(() -> f())
                .start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Trying to call f()");
            f();
        }catch (Exception e){
            System.out.println("Interrupted from SynchronizedBlocked");
        }
        System.out.println("Exiting SynchronizedBlocked.run()");
    }
}