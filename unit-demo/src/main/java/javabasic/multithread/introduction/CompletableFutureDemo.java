package javabasic.multithread.introduction;

import java.util.concurrent.*;

/**
 * @author lee
 * @since 2022/6/14
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.使用CompletableFuture内部线程
        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("线程休眠中断异常");
            }
            System.out.println("内部线程执行结束...");
            return "1.Hello CompletableFuture!";
        });
        System.out.println("1.主线程执行中...");
        System.out.println("结果为：" + cf1.get());

        //1.自定义新线程
        CompletableFuture<String> cf2 = new CompletableFuture();
        Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("线程休眠中断异常");
            }
            System.out.println("自定义线程执行结束...");
            cf2.complete("2.Hello CompletableFuture!");
        });

        thread.start();
        System.out.println("2.主线程执行中...");
        System.out.println("结果为：" + cf2.get());
    }
}
