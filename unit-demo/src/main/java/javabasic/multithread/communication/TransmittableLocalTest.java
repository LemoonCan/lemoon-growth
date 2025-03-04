package javabasic.multithread.communication;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lee
 * @since 2024/9/14
 */
public class TransmittableLocalTest {
    // 创建一个TransmittableThreadLocal来保存线程间需要传递的用户ID
    private static final TransmittableThreadLocal<String> userIdHolder = new TransmittableThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 在父线程中设置用户ID
        userIdHolder.set("User-123");

        // 提交任务到线程池
        Runnable task = TtlRunnable.get(() -> {
            // 在这里得到用户ID
            String userId = userIdHolder.get();
        });

        executorService.submit(task);

        executorService.shutdown();
    }

}
