package com.chunjiez.business.service.task;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunjiez.common.constant.BusinessCode;
import com.chunjiez.common.constant.TaskExecuteStatus;
import com.chunjiez.common.entity.exception.BusinessException;
import com.chunjiez.common.util.RandomUtils;
import com.chunjiez.database.dao.TaskDao;
import com.chunjiez.database.entity.po.Task;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 任务表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskServiceImpl extends ServiceImpl<TaskDao, Task> implements TaskService {

    private final TaskDao taskDao;

    @Override
    public Task getByTaskId(Long taskId) {
        Task task = taskDao.selectById(taskId);
        if (task == null) {
            log.warn("task [{}] not exist.", taskId);
            throw new BusinessException(BusinessCode.TASK_NOT_EXIST);
        }
        return task;
    }

    @Override
    public Task createTask(String taskName, String logo, String description) {
        Task sameNameTask = taskDao.selectByName(taskName);
        if (Objects.nonNull(sameNameTask)) {
            throw new BusinessException(BusinessCode.TASK_NAME_DUPLICATE);
        }
        Task task = new Task()
                .setTaskSn(RandomUtils.dateRandom(10))
                .setName(taskName).setLogo(logo)
                .setDescription(description)
                .setStatus(TaskExecuteStatus.SUCCESS.getStatus());
        taskDao.insert(task);
        return task;
    }
}
