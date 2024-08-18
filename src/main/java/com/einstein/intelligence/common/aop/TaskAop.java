package com.einstein.intelligence.common.aop;

import com.einstein.intelligence.entity.annotation.TaskAnnotation;
import com.einstein.intelligence.entity.constant.TaskStatusEnum;
import com.einstein.intelligence.exception.SystemException;
import com.einstein.intelligence.util.JsonUtils;
import com.einstein.intelligence.util.SpringExpressionUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/20
 */
@Slf4j
@Aspect
@Component
public class TaskAop {

    @Pointcut("@annotation(com.einstein.intelligence.entity.annotation.TaskAnnotation)")
    public void taskStepPoint() {
    }

    @Around("taskStepPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TaskAnnotation taskAnnotation = method.getAnnotation(TaskAnnotation.class);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return joinPoint.proceed();
    }



}
