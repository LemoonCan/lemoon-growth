package javabasic.multithread.terminate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lee
 * @since 2/25/21
 */
public class ReentranLockInterrupting {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt");
        t.interrupt();
    }
}

class BlockedMutex{
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        lock.lock();
    }

    public void f(){
        try {
            System.out.println("locked acquired in f() 1");
            lock.lockInterruptibly();
            System.out.println("locked acquired in f() 2");
        } catch (InterruptedException e) {
            System.out.println("Interrupted from lock acquisition in f()");
        }
        System.out.println("blocked exit()");
    }
}

class Blocked2 implements Runnable{
    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        blocked.f();
        System.out.println("Broken out of blocked call");
    }
}