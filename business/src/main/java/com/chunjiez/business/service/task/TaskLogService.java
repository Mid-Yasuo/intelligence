package com.chunjiez.business.service.task;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunjiez.database.entity.po.TaskLog;

/**
 * <p>
 * 任务日志表 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
public interface TaskLogService extends IService<TaskLog> {

    /**
     * 创建任务执行日志
     *
     * @param taskId
     * @return
     */
    TaskLog createTaskExecuteLog(Long taskId);

    /**
     * 获取任务执行日志
     *
     * @param taskId
     * @param status
     * @return
     */
    TaskLog getTaskLog(Long taskId, Integer status);
}
