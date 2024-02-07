package com.einstein.albert.intelligence.common.aop;

import com.einstein.albert.intelligence.dao.ScheduleTaskLogDao;
import com.einstein.albert.intelligence.entity.annotation.TaskAnnotation;
import com.einstein.albert.intelligence.entity.constant.TaskStatusEnum;
import com.einstein.albert.intelligence.entity.po.ScheduleTaskLog;
import com.einstein.albert.intelligence.exception.SystemException;
import com.einstein.albert.intelligence.util.JsonUtils;
import com.einstein.albert.intelligence.util.SpringExpressionUtils;
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


    private ScheduleTaskLogDao scheduleTaskLogDao;


    @Autowired
    public void setScheduleTaskLogDao(ScheduleTaskLogDao scheduleTaskLogDao) {
        this.scheduleTaskLogDao = scheduleTaskLogDao;
    }

    @Pointcut("@annotation(com.einstein.albert.intelligence.entity.annotation.TaskAnnotation)")
    public void taskStepPoint() {
    }

    @Around("taskStepPoint()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        TaskAnnotation taskAnnotation = method.getAnnotation(TaskAnnotation.class);
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        ScheduleTaskLog scheduleTaskLog = new ScheduleTaskLog()
                .setTaskName(taskAnnotation.name())
                .setTaskClass(joinPoint.getTarget().getClass().getName())
                .setTaskMethod(method.getName())
                .setTaskParamTypes(JsonUtils.toJson(SpringExpressionUtils.getMethodParamTypes(method)))
                .setTaskParams(JsonUtils.toJson(args))
                .setTaskStatus(TaskStatusEnum.STARTED.getCode())
                .setExecuteDate(Integer.parseInt(today));
        scheduleTaskLogDao.insert(scheduleTaskLog);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Object proceed = joinPoint.proceed();
            scheduleTaskLog.setTaskStatus(TaskStatusEnum.SUCCESS.getCode());
            return proceed;
        } catch (Throwable throwable) {
            scheduleTaskLog.setTaskStatus(TaskStatusEnum.ERROR.getCode())
                    .setErrorMsg(throwable.getMessage());
            throw new SystemException(throwable);
        } finally {
            stopWatch.stop();
            scheduleTaskLog.setTs(stopWatch.getLastTaskTimeMillis());
            scheduleTaskLogDao.updateById(scheduleTaskLog);
        }
    }



}
