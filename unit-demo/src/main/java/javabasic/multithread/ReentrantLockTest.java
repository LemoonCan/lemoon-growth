package javabasic.multithread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lee
 * @since 2020-07-06
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        //公平锁
        ReentrantLock fairReentrantLock = new ReentrantLock(true);
        //非公平锁
        ReentrantLock unfairReentrantLock = new ReentrantLock(false);

        fairReentrantLock.unlock();
    }
}
