package javabasic.multithread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author lee
 * @date 2/25/21
 */
public class FibonacciTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU核数：" + Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        Fibonacci fibonacci = new Fibonacci(6);
        Future<Integer> future = forkJoinPool.submit(fibonacci);
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println(String.format("耗时：%d millis", end - start));
    }
}

class Fibonacci extends RecursiveTask<Integer> {

    int n;

    public Fibonacci(int n) {
        this.n = n;
    }

    /**
     * 实现逻辑在compute()里
     * 区分何时返回，何时拆分任务，拆分任务返回后如何整合
     *
     * @return
     */
    @Override
    protected Integer compute() {
        // 阈值内直接计算
        if (n <= 1) {
            return n;
        } else {
            // 阈值外进行拆分
            // f(n-1)
            Fibonacci f1 = new Fibonacci(n - 1);
            f1.fork();
            // f(n-2)
            Fibonacci f2 = new Fibonacci(n - 2);
            f2.fork();
            // f(n) = f(n-1) + f(n-2)
            return f1.join() + f2.join();
        }
    }
}