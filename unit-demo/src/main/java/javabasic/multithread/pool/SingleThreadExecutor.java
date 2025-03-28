package javabasic.multithread.pool;

import javabasic.multithread.introduction.runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lee
 * @since 2020-11-05
 * 线程数量为1的FixedThreadPool
 * 适用于长期存活的任务
 * 如果向SingleThreadExecutor提交了多个任务，这些任务将排队，并按任务提交的顺序执行(任务会被序列化？？？，并维护自己的悬挂任务队列)
 */
public class SingleThreadExecutor {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
