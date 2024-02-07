package com.einstein.albert.intelligence.common.configuration.async;

import com.einstein.albert.intelligence.dao.ScheduleTaskDao;
import com.einstein.albert.intelligence.entity.constant.CommonConst;
import com.einstein.albert.intelligence.entity.po.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/12/19
 */
@Slf4j
@EnableScheduling
@EnableAsync
@Component
public class AsyncConfiguration {

    @Value("${async.coreSize}")
    private Integer coreSize;

    private final ScheduleTaskDao scheduleTaskDao;

    private TraceTaskDecorator traceTaskDecorator;

    public AsyncConfiguration(ScheduleTaskDao scheduleTaskDao) {
        this.scheduleTaskDao = scheduleTaskDao;
    }

    @Autowired
    public void setTraceTaskDecorator(TraceTaskDecorator traceTaskDecorator) {
        this.traceTaskDecorator = traceTaskDecorator;
    }


    @Bean("taskThreadPool")
    public ThreadPoolTaskExecutor taskThreadPool() {
        ThreadPoolTaskExecutor scheduler = new ThreadPoolTaskExecutor();
        scheduler.setCorePoolSize(coreSize);
        scheduler.setMaxPoolSize(2 * coreSize);
        scheduler.setQueueCapacity(300);
        scheduler.setTaskDecorator(traceTaskDecorator);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        scheduler.setThreadNamePrefix("schedule-task-thread-");
        scheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        scheduler.initialize();
        return scheduler;
    }

    public Runnable getRunnable(ScheduleTask scheduleTask) {
        return () -> {
            try {
                Class<?> taskClass = Class.forName(scheduleTask.getTaskClass());
                Object instance = taskClass.newInstance();
                Method method = taskClass.getMethod(scheduleTask.getTaskMethod());
                method.invoke(instance);
            } catch (Exception exception) {
                log.error("invoke error.{}", exception.getMessage());
            }
        };
    }
}
