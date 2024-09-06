package com.einstein.intelligence.configuration.mybatis.handler;

import java.util.Arrays;
import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/6
 */
public interface IgnoreDataPermissionHandler {

    /**
     * 获取忽略数据权限的表列表
     *
     * @return
     */
    default List<String> ignoreTables() {
        return Arrays.asList("cmp_role");
    }
}
