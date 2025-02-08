package com.chunjiez.common.entity.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DistributedLock {

    boolean lockMethod() default false;

    String keyPrefix() default "";

    String keyValue() default "";

    int lockWaitTime() default 0;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

}
