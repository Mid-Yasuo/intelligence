package com.einstein.intelligence.configuration.aop;

import com.einstein.intelligence.entity.annotation.DistributedLock;
import com.einstein.intelligence.exception.SystemException;
import com.einstein.intelligence.service.DistributedLockService;
import com.einstein.intelligence.util.SpringExpressionUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Aspect
@Component
@Slf4j
public class DistributedLockAop {


    private DistributedLockService distributedLockService;

    @Autowired
    public void setDistributedLockService(DistributedLockService distributedLockService) {
        this.distributedLockService = distributedLockService;
    }

    /**
     * <p>controller 包下所有子包所有方法 @Pointcut("execution(* com.gateway.admin.controller..*.*(..))")</p>
     * <code>切点为包含注解 @Pointcut("@annotation(com.gateway.common.domain.annotation.OperationRecord)")</code>
     *
     * @author 张春杰
     * @date 2023/3/21
     */
    @Pointcut("@annotation(com.einstein.intelligence.entity.annotation.DistributedLock)")
    public void distributedLockPoint() {
    }

    @Around("distributedLockPoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributedLock distributedLock = method.getAnnotation(DistributedLock.class);
        String redisKey = getRedisKey(joinPoint, method, distributedLock);
        return distributedLockService.lockRun(redisKey, distributedLock.lockWaitTime(), distributedLock.timeUnit(), () -> {
            try {
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                throw new SystemException(throwable);
            }
        });
    }

    private String getRedisKey(ProceedingJoinPoint joinPoint, Method method, DistributedLock distributedLock) {
        String keyPrefix = distributedLock.keyPrefix();
        String keyValue = distributedLock.keyValue();
        String redisKey;
        if (distributedLock.lockMethod()) {
            redisKey = joinPoint.getTarget().getClass().getName();
        } else if (StringUtils.isNotBlank(keyPrefix)) {
            redisKey = keyPrefix + SpringExpressionUtils.getValueBySpringExpression(keyValue, method, joinPoint.getArgs());
        } else {
            redisKey = joinPoint.getTarget().getClass().getName() + "#" + method.getName();
        }
        return redisKey;
    }

}
