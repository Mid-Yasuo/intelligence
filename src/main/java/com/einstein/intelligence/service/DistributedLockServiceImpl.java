package com.einstein.intelligence.service;

import com.einstein.intelligence.configuration.DistributedLockClient;
import com.einstein.intelligence.util.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/23
 */
@Slf4j
@Service
public class DistributedLockServiceImpl implements DistributedLockService {

    private DistributedLockClient distributedLockClient;

    @Autowired
    public void setDistributedLockClient(DistributedLockClient distributedLockClient) {
        this.distributedLockClient = distributedLockClient;
    }

    @Override
    public <T> T lockRun(String lock, long waitTime, TimeUnit timeUnit, Function<T> function) {
        boolean acquireLock = distributedLockClient.acquireLock(lock, waitTime, timeUnit);
        log.info("get distributed lock [{}] result:{}", lock, acquireLock);
        if (acquireLock) {
            long start = System.currentTimeMillis();
            try {
                return function.get();
            } finally {
                log.info("DistributedLock [{}] ts: {}", lock, System.currentTimeMillis() - start);
                distributedLockClient.releaseLock(lock);
            }
        }
        return null;
    }
}
