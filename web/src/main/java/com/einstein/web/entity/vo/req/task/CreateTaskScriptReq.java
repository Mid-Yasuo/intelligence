package com.einstein.web.entity.vo.req.task;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Data
public class CreateTaskScriptReq {

    @NotNull
    private Long taskId;

    @NotNull
    private Long taskStepId;
    /**
     * 执行时机： Before-请求前 After-请求后
     */
    @NotEmpty
    private String scriptPosition;
    /**
     * 操作： get-获取参数 set-设置参数 download-下载 unzip-解压 zip-压缩文件
     */
    @NotEmpty
    private String scriptOperation;

    private String targetKey;
    /**
     * 目标类型：json | html | file
     */
    private String targetType;

    private String targetValue;
}
