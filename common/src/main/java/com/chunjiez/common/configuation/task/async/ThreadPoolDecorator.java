package com.chunjiez.common.configuation.task.async;

import com.chunjiez.common.constant.Constant;
import com.chunjiez.common.util.RandomUtils;
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
public class ThreadPoolDecorator implements TaskDecorator {

    @Override
    @NonNull
    public Runnable decorate(@NonNull Runnable runnable) {
        String traceId = StringUtils.isBlank(MDC.get(Constant.TRACE_ID)) ? RandomUtils.generateTraceId() : MDC.get(Constant.TRACE_ID);
        return () -> {
            try {
                MDC.put(Constant.TRACE_ID, traceId);
                runnable.run();
            } finally {
                MDC.remove(Constant.TRACE_ID);
            }
        };
    }
}
