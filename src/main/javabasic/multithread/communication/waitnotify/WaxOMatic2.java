package main.javabasic.multithread.communication.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lee
 * @date 2020-12-01
 * 使用 condition.await、signal/signalAll 需获得显式的锁
 */
public class WaxOMatic2 {
    public static void main(String[] args) throws InterruptedException {
        Car2 car = new Car2();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff2(car));
        executorService.execute(new WaxOn2(car));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}

class Car2 {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn = false;

    public void waxed() {
        lock.lock();
        try {
            waxOn = true;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void buffed() {
        lock.lock();
        try {
            waxOn = false;
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    public void waitForWaxing() throws InterruptedException {
        lock.lock();
        try {
            while (!waxOn) {
                condition.await();
            }
        }finally {
            lock.unlock();
        }
    }

    public void waitForBuffing() throws InterruptedException {
        lock.lock();
        try {
            //如何保证执行
            while (waxOn) {
                condition.await();
            }
        }finally {
           lock.unlock();
        }
    }
}

class WaxOn2 implements Runnable {
    private Car2 car;

    public WaxOn2(Car2 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("Wax on! ");
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("Wax on! "+200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt(wax on)");
        }
        System.out.println("Ending Wax on task");
    }
}

class WaxOff2 implements Runnable {
    private Car2 car;

    public WaxOff2(Car2 car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("Wax Off! "+200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("Exiting via interrupt(wax off)");
        }
        System.out.println("Ending Wax off task");
    }
}
