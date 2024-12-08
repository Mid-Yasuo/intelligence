package com.einstein.service.task;

import com.baomidou.mybatisplus.extension.service.IService;
import com.einstein.database.entity.po.TaskScript;

import java.util.List;

/**
 * <p>
 * 任务脚本表 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
public interface TaskScriptService extends IService<TaskScript> {

    /**
     * 获取任务步骤关联的脚本列表
     *
     * @param taskId
     * @param taskStepId
     * @param position
     * @return
     */
    List<TaskScript> listTaskStepScripts(Long taskId, Long taskStepId, String position);

    /**
     * 创建任务脚本
     *
     * @param taskId
     * @param taskStepId
     * @param scriptPosition
     * @param scriptOperation
     * @param targetType
     * @param targetKey
     * @param targetValue
     * @return
     */
    TaskScript createTaskScript(Long taskId, Long taskStepId, String scriptPosition,
                                String scriptOperation, String targetType, String targetKey, String targetValue);
}
