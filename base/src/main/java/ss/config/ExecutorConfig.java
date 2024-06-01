package ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import ss.common.utils.PropertiesUtils;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author tjr
 */
@Configuration
@EnableAsync
public class ExecutorConfig {

    @Bean(name = "asyncServiceExecutor", destroyMethod = "shutdown")
    public Executor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(PropertiesUtils.getInt("async.executor.thread.core_pool_size"));
        //配置最大线程数
        executor.setMaxPoolSize(PropertiesUtils.getInt("async.executor.thread.max_pool_size"));
        //配置队列大小
        executor.setQueueCapacity(PropertiesUtils.getInt("async.executor.thread.queue_capacity"));
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix(PropertiesUtils.getProperty("async.executor.thread.name.prefix"));
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}