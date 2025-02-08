package com.chunjiez.common.configuation.pool;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/8
 */
public class DistributedLockPool {

    public static final ScheduledThreadPoolExecutor SCHEDULED_THREAD_POOL_EXECUTOR = new ScheduledThreadPoolExecutor(30,
            new ThreadFactory() {
                final AtomicInteger atomicInteger = new AtomicInteger();
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "distributed-lock-pool-" + atomicInteger.getAndIncrement());
                }
            });


    public static Future<?> submit(Runnable runnable) {
        return SCHEDULED_THREAD_POOL_EXECUTOR.submit(runnable);
    }

}
