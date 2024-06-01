package ss.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import ss.common.utils.PropertiesUtils;

/**
 * 定时任务的配置
 *
 * @author tjr
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskScheduler());
    }

    @Bean
    public TaskScheduler taskScheduler() {
        // Spring提供的定时任务线程池类
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        // 设定最大可用的线程数目
        taskScheduler.setPoolSize(PropertiesUtils.getInt("async.executor.thread.max_pool_size"));
        return taskScheduler;
    }
}
