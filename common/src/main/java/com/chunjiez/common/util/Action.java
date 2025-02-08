package com.chunjiez.common.util;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/20
 */
public interface Action<I,O> {

    /**
     * <p>根据输入获取输出</p>
     *
     * @param i - [I]
     * @return O
     * @author 张春杰
     * @date 2024/1/20
     */
    O run(I i);
}
