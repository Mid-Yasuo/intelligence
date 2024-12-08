package com.einstein.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
public enum OperateResourceTypeEnum {
    /**
     * 目标类型：json | html | file
     */
    VARIABLE("variable", ""),
    JSON("json", "$"),
    HTML("html", "#"),
    file("file", ""),
    ;

    @Getter
    private final String type;

    @Getter
    private final String prefix;

    OperateResourceTypeEnum(String type, String prefix) {
        this.type = type;
        this.prefix = prefix;
    }
}
