package javabasic.multithread.communication.waitnotify;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 2020-11-30
 * notifyAll 唤醒所有等待相同锁的任务
 * notify 唤醒一个等待相同锁的任务,唤醒的任务由线程调度算法决定
 */
public class NotifyVsNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task());
        }
        executorService.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;
            @Override
            public void run() {
                if(prod){
                    System.out.println("\nnotify()");
                    Task.blocker.prod();
                    prod = false;
                }else{
                    System.out.println("\nnotifyAll()");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        },400,400);
        TimeUnit.SECONDS.sleep(5);

        System.out.println("\nStart timer canceled");
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("Task2.blocker.prodAll()");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nshutting down");

        executorService.shutdownNow();
    }
}

class Blocker {
    synchronized void waitingAll(){
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.println(Thread.currentThread() + " ");
            }
        }catch (InterruptedException e){

        }
    }

    synchronized void prod(){
        notify();
    }

    synchronized void prodAll(){
        notifyAll();
    }
}

class Task implements Runnable{
    static Blocker blocker = new Blocker();
    @Override
    public void run() {
        blocker.waitingAll();
    }
}

class Task2 implements Runnable{
    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.waitingAll();
    }
}