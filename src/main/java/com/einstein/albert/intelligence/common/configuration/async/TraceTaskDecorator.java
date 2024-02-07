package com.einstein.albert.intelligence.common.configuration.async;

import com.einstein.albert.intelligence.entity.constant.CommonConst;
import com.einstein.albert.intelligence.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/11
 */
@Slf4j
@Component
public class TraceTaskDecorator implements TaskDecorator {

    @Override
    @NonNull
    public Runnable decorate(@NonNull Runnable runnable) {
        String traceId = StringUtils.isBlank(MDC.get(CommonConst.TRACE_ID)) ? RandomUtils.generateTraceId() : MDC.get(CommonConst.TRACE_ID);
        return () -> {
            try {
                MDC.put(CommonConst.TRACE_ID, traceId);
                runnable.run();
            } finally {
                MDC.remove(CommonConst.TRACE_ID);
            }
        };
    }
}
