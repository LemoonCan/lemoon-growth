package javabasic.multithread.pool;

import javabasic.multithread.introduction.runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lee
 * @since 2020-11-05
 * 为每个任务都创建一个线程
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.submit(new LiftOff());
        //防止新任务提交到当前线程池
        exec.shutdown();
    }
}
