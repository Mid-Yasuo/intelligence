package com.einstein.service;

import com.einstein.common.configuation.DistributedLockPool;
import com.einstein.common.entity.dto.DelayedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/8
 */
@Slf4j
@Component
public class DistributedLockClient {

    private final DelayQueue<DelayedLock> delayedLocks = new DelayQueue<>();

    private static final Long INTERVAL_CHECK_TIME = 1000L;

    private static final int EXPIRATION_TIME = 30;

    private static final String LOCK_VALUE = "1";

    private final RedisTemplate<String, Object> redisTemplate;

    public DistributedLockClient(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.init();
    }

    private void init() {
        DistributedLockPool.submit(() -> {
            while (true) {
                try {
                    DelayedLock delayedLock = delayedLocks.take();
                    if (Boolean.FALSE.equals(redisTemplate.hasKey(delayedLock.getLock()))) {
                        continue;
                    }
                    long lockExpire = Optional.ofNullable(redisTemplate.getExpire(delayedLock.getLock(), TimeUnit.SECONDS)).orElse(0L);
                    //过期时间大于10s，则重新放回队列
                    if (lockExpire >= 10) {
                        delayedLocks.add(new DelayedLock(delayedLock.getLock(), INTERVAL_CHECK_TIME));
                        continue;
                    }
                    boolean renewalResult = renewal(delayedLock.getLock());
                    log.debug("distributed lock[{}] renewal result:{}", delayedLock.getLock(), renewalResult);
                    if (renewalResult) {
                        delayedLocks.add(new DelayedLock(delayedLock.getLock(), INTERVAL_CHECK_TIME));
                    }
                } catch (InterruptedException exception) {
                    log.error("get delayed queue error.{}", exception.getMessage());
                    break;
                }
            }
        });
    }


    /**
     * <p>锁续约，默认重试三次</p>
     *
     * @param lock - [String]
     * @return boolean
     * @author 张春杰
     * @date 2024/1/9
     */
    private boolean renewal(String lock) {
        int count = 0;
        do {
            if (Boolean.TRUE.equals(redisTemplate.expire(lock, EXPIRATION_TIME, TimeUnit.SECONDS))) {
                return true;
            }
            count++;
        } while (count < 3);
        return false;
    }

    public boolean acquireLock(String lockKey, long waitTime, TimeUnit timeUnit) {
        long now = System.currentTimeMillis();
        long expireStamp = System.currentTimeMillis() + timeUnit.toMillis(waitTime);
        while (now <= expireStamp) {
            if (preemptLock(lockKey)) {
                //获取到锁
                delayedLocks.add(new DelayedLock(lockKey, INTERVAL_CHECK_TIME));
                return true;
            }
            sleep(10);
            now = System.currentTimeMillis();
        }
        return false;
    }

    /**
     * <p>抢占 key</p>
     *
     * @param lockKey - [String]
     * @return boolean
     * @author 张春杰
     * @date 2024/1/9
     */
    public boolean preemptLock(String lockKey) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(lockKey, LOCK_VALUE, EXPIRATION_TIME, TimeUnit.SECONDS));
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            boolean interrupted = Thread.interrupted();
            log.error("thread interrupted:{}", interrupted);
        }
    }

    public void releaseLock(String lock) {
        Boolean deleteResult = redisTemplate.delete(lock);
        log.debug("releaseLock: {}. deleteResult = {}", lock, deleteResult);
    }
}
