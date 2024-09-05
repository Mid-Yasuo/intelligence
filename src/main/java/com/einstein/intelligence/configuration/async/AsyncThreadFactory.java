package com.einstein.intelligence.configuration.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/7/5
 */
@Slf4j
public class AsyncThreadFactory extends ScheduledThreadPoolExecutor {

    public AsyncThreadFactory(int corePoolSize, String threadPoolName) {
        super(corePoolSize, new ThreadFactory() {
            final AtomicInteger atomic = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, threadPoolName + "-" + atomic.getAndIncrement());
            }
        });
        this.setMaximumPoolSize(corePoolSize);
    }

    @Override
    public void setMaximumPoolSize(int maximumPoolSize) {
        super.setMaximumPoolSize(maximumPoolSize);
    }

    @Override
    public ScheduledFuture<?> schedule(@NonNull Runnable command, long delay, @NonNull TimeUnit unit) {
        return super.schedule(command, delay, unit);
    }

    @Override
    public <V> ScheduledFuture<V> schedule(@NonNull Callable<V> callable, long delay, @NonNull TimeUnit unit) {
        return super.schedule(callable, delay, unit);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(@NonNull Runnable command, long initialDelay, long period, @NonNull TimeUnit unit) {
        return super.scheduleAtFixedRate(command, initialDelay, period, unit);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (Objects.nonNull(t)) {
            log.error("异步线程执行失败：", t);
        }
    }


}
