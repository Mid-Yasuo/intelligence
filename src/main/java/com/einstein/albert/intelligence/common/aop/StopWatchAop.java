package com.einstein.albert.intelligence.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/25
 */
@Slf4j
@Aspect
@Component
@ConditionalOnProperty(prefix = "stopWatch.enable", value = "true", matchIfMissing = true)
public class StopWatchAop implements Ordered {

    @Override
    public int getOrder() {
        return 1;
    }

    @Pointcut("@annotation(com.einstein.albert.intelligence.entity.annotation.GetExecutionTime)")
    public void stopWatchPoint() {
    }

    @Around("stopWatchPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            return joinPoint.proceed();
        } finally {
            stopWatch.stop();
            long seconds = stopWatch.getLastTaskTimeMillis();
            log.info("\n ============= stop watch =============" +
                    "\n class : {}" +
                    "\n method: {}" +
                    "\n ts    : {}" +
                    "\n ============= stop watch =============", clazz, methodName, seconds);
        }
    }
}
