package com.einstein.web.entity.vo.req.task;

import lombok.Data;

import java.util.Map;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Data
public class CreateTaskStepReq {

    private Long taskId;

    private String taskStepName;

    private String requestUrl;

    private String requestMethod;

    private Map<String, Object> requestHeaders;

    private Map<String, Object> requestBody;
    /**
     * 排序
     */
    private Integer taskStepSort;
}
