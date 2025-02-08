package com.chunjiez.common.constant;

import lombok.Getter;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/9
 */
public enum ExtraOption {

    /**
     * 获取对应的参数
     */
    REQ_HEADER("req.header."),
    REQ_BODY("req.body."),
    RES_HEADER("res.header."),
    RES_BODY("res.body."),
    ;

    @Getter
    private final String prefix;

    ExtraOption(String prefix) {
        this.prefix = prefix;
    }


}
