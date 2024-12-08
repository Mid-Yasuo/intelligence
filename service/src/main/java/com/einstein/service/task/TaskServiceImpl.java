package com.einstein.service.task;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.common.constant.BusinessEnum;
import com.einstein.common.constant.TaskExecuteStatusEnum;
import com.einstein.common.entity.exception.BusinessException;
import com.einstein.common.util.RandomUtils;
import com.einstein.database.dao.TaskDao;
import com.einstein.database.entity.po.Task;
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
            throw new BusinessException(BusinessEnum.TASK_NOT_EXIST);
        }
        return task;
    }

    @Override
    public Task createTask(String taskName, String logo, String description) {
        Task sameNameTask = taskDao.selectByName(taskName);
        if (Objects.nonNull(sameNameTask)) {
            throw new BusinessException(BusinessEnum.TASK_NAME_DUPLICATE);
        }
        Task task = new Task()
                .setTaskSn(RandomUtils.dateRandom(10))
                .setName(taskName).setLogo(logo)
                .setDescription(description)
                .setStatus(TaskExecuteStatusEnum.SUCCESS.getCode());
        taskDao.insert(task);
        return task;
    }
}
