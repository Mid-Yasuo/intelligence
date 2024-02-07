package com.einstein.albert.intelligence.util;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/23
 */
@FunctionalInterface
public interface Function<T> {

    /**
     * <p>get result</p>
     *
     * @return T
     * @author 张春杰
     * @date 2024/1/23
     */
    T get();
}
