package com.einstein.common.entity.dto;

import lombok.Getter;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/8
 */
@Getter
public class DelayedLock implements Delayed {

    private final String lock;

    private final long expire;

    public DelayedLock(String lock, long expire) {
        this.lock = lock;
        this.expire = System.currentTimeMillis() + expire;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed delayed) {
        DelayedLock delayedLock = (DelayedLock) delayed;
        return lock.compareTo(delayedLock.getLock());
    }
}
