package javabasic.multithread.introduction;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author lee
 * @date 2020-07-13
 */
public class CallableRunnableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        MyTask1 task1 = new MyTask1();
        Future<Integer> result = executor.submit(task1);
        System.out.println(result.get());

        FutureTask<Integer> futureTask = new FutureTask(new MyTask1());
        executor.submit(futureTask);
        System.out.println(futureTask.get());
    }

    /**
     * Callable 开启一个线程执行任务，并且任务执行完后有返回值
     */
    public static class MyTask1 implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(1000);
            return 2;
        }
    }
}
