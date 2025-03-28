package javabasic.multithread.communication.waitnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lee
 * @since 5/8/21
 * A:5,B:10,C:15
 * A,B,C依次干活，重复10次
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}

class ShareResource {
    private int number = 1;

    ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    private void print(int times, Condition await, Condition signal, int workNo, int signalNo) {
        lock.lock();
        try {
            //1.判断(保证 A、B、C 可以按顺序执行)
            while (number != workNo) {
                await.await();
            }

            //2.干活
            for (int i = 0; i < times; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知
            number = signalNo;
            signal.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void print5()  {
        print(5, c1, c2, 1, 2);
    }

    void print10() {
        print(10, c2, c3, 2, 3);
    }

    void print15() {
        print(15, c3, c1, 3, 1);
    }
}
