package main.javabasic.multithread.communication.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @date 2020-11-29
 * sleep等待时间占有锁，模拟打蜡抛光的时间
 * 恰好也保证了Car.waxOn on变更-on读取-off变更-off读取 的线性顺序
 * wait/notify
 */
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new WaxOff(car));
        executorService.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(1);
        executorService.shutdownNow();
    }
}

class Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notifyAll();
    }

    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn) {
            wait();
        }
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        //如何保证执行
        while (waxOn) {
            wait();
        }
    }
}

class WaxOn implements Runnable {
    private Car car;

    public WaxOn(Car car) {
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

class WaxOff implements Runnable {
    private Car car;

    public WaxOff(Car car) {
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
