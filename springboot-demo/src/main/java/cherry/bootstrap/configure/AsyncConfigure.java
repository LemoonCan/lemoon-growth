package cherry.bootstrap.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author lee
 * @since 2022/3/17
 */
@Configuration
public class AsyncConfigure {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        threadPoolTaskExecutor.setCorePoolSize(10);
        // 最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(20);
        // 队列容量
        threadPoolTaskExecutor.setQueueCapacity(20);
        // 活跃时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        // 主线程等待子线程执行时间
        threadPoolTaskExecutor.setAwaitTerminationSeconds(60);
        // 线程名字前缀
        threadPoolTaskExecutor.setThreadNamePrefix("sun");
        // RejectedExecutionHandler:当pool已经达到max-size的时候，如何处理新任务
        // CallerRunsPolicy:不在新线程中执行任务，而是由调用者所在的线程来执行
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
