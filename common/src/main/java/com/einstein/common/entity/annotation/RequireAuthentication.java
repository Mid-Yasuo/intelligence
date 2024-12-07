package com.einstein.common.entity.annotation;

import java.lang.annotation.*;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequireAuthentication {

    boolean authentication() default true;
}
