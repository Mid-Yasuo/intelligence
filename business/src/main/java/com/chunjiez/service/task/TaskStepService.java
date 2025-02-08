package com.chunjiez.service.task;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunjiez.database.entity.po.TaskStep;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 任务步骤表 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
public interface TaskStepService extends IService<TaskStep> {

    /**
     * 根据任务查询任务步骤
     *
     * @param taskId
     * @return
     */
    List<TaskStep> listByTaskId(Long taskId);

    /**
     * 根据id 查询步骤
     *
     * @param taskStepId
     * @return
     */
    TaskStep getByTaskStepId(Long taskStepId);

    /**
     * 创建任务步骤
     *
     * @param taskId
     * @param taskStepName
     * @param requestUrl
     * @param requestMethod
     * @param requestHeaders
     * @param requestBody
     * @param stepSort
     * @return
     */
    TaskStep createTaskStep(Long taskId,
                            String taskStepName,
                            String requestUrl,
                            String requestMethod,
                            Map<String, Object> requestHeaders,
                            Map<String, Object> requestBody,
                            Integer stepSort);
}
