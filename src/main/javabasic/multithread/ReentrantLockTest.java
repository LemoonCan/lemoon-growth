package main.javabasic.multithread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lee
 * @date 2020-07-06
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        //公平锁
        ReentrantLock failReentrantLock = new ReentrantLock(true);
        //非公平锁
        ReentrantLock unfailReentrantLock = new ReentrantLock(false);
    }
}
