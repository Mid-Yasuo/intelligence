package com.chunjiez.web.entity.vo.resp.task;

import com.chunjiez.common.util.JsonUtils;
import com.chunjiez.database.entity.po.TaskStep;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Data
@Accessors(chain = true)
public class TaskStepResp {

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

    @SuppressWarnings("unchecked")
    public static TaskStepResp build(TaskStep taskStep) {
        return new TaskStepResp().setTaskId(taskStep.getTaskId())
                .setTaskStepName(taskStep.getTaskStepName())
                .setRequestUrl(taskStep.getRequestUrl())
                .setRequestMethod(taskStep.getRequestMethod())
                .setRequestHeaders(JsonUtils.toJavaBean(taskStep.getRequestHeaders(), Map.class))
                .setRequestBody(JsonUtils.toJavaBean(taskStep.getRequestBody(), Map.class))
                .setTaskStepSort(taskStep.getTaskStepSort());
    }
}
