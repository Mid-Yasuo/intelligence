package com.chunjiez.business.service.task;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chunjiez.database.entity.po.Task;

/**
 * <p>
 * 任务表 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
public interface TaskService extends IService<Task> {

    Task getByTaskId(Long taskId);

    /**
     * 创建任务
     * @param taskName
     * @param logo
     * @param description
     * @return Task
     */
    Task createTask(String taskName, String logo, String description);
}
