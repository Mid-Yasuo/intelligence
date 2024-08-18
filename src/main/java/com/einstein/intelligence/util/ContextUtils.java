/*
 * Copyright (c) 2023.  All rights reserved.
 */

package com.einstein.intelligence.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2023/3/15
 */
@Component
public class ContextUtils implements ApplicationContextAware {

    /**
     * Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        ContextUtils.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    public static void publish(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }
}
