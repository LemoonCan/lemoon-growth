package main.javabasic.multithread.share;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lee
 * @date 2020-11-16
 */
public class MutexEventGenerator extends IntGenerator {
    private int currentEventValue = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public int next() {
        lock.lock();
        try{
            ++currentEventValue;
            //使失败发生的频繁点
            Thread.yield();
            ++currentEventValue;
            return currentEventValue;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EventChecker.test(new MutexEventGenerator());
    }
}
