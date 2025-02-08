package com.chunjiez.business.service.lock;

import com.chunjiez.common.util.Function;

import java.util.concurrent.TimeUnit;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/23
 */
public interface DistributedLockService {

    /**
     * <p>分布式执行</p>
     *
     * @param lock     - [String]
     * @param waitTime - [long]
     * @param timeUnit - [TimeUnit]
     * @param runnable - [Function<T>]
     * @return T
     * @author 张春杰
     * @date 2024/1/23
     */
    <T> T lockRun(String lock, long waitTime, TimeUnit timeUnit, Function<T> runnable);
}
