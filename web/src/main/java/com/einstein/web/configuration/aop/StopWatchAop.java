package com.einstein.web.configuration.aop;

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

import java.lang.reflect.Method;

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

    @Pointcut("@annotation(com.einstein.common.entity.annotation.GetExecutionTime)")
    public void stopWatchPoint() {
    }

    @Around("stopWatchPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = method.getDeclaringClass();
        String methodName = method.getName();
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
