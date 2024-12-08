package com.einstein.service.task;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.common.constant.BusinessEnum;
import com.einstein.common.entity.exception.BusinessException;
import com.einstein.common.util.JsonUtils;
import com.einstein.database.dao.TaskStepDao;
import com.einstein.database.entity.po.Task;
import com.einstein.database.entity.po.TaskStep;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 任务步骤表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskStepServiceImpl extends ServiceImpl<TaskStepDao, TaskStep> implements TaskStepService {

    private final TaskStepDao taskStepDao;

    private final TaskService taskService;

    @Override
    public List<TaskStep> listByTaskId(Long taskId) {
        return taskStepDao.listByTaskId(taskId);
    }

    @Override
    public TaskStep getByTaskStepId(Long taskStepId) {
        TaskStep taskStep = taskStepDao.selectById(taskStepId);
        if (Objects.isNull(taskStep)) {
            throw new BusinessException(BusinessEnum.TASK_STEP_NOT_EXIST);
        }
        return taskStep;
    }

    @Override
    public TaskStep createTaskStep(Long taskId, String taskStepName, String requestUrl,
                                   String requestMethod, Map<String, Object> requestHeaders, Map<String, Object> requestBody,
                                   Integer stepSort) {
        Task task = taskService.getByTaskId(taskId);
        TaskStep sameTaskStep = taskStepDao.selectByStepName(taskId, taskStepName);
        if (Objects.nonNull(sameTaskStep)) {
            throw new BusinessException(BusinessEnum.TASK_STEP_NAME_DUPLICATE);
        }
        TaskStep taskStep = new TaskStep().setTaskId(taskId)
                .setTaskStepName(taskStepName)
                .setRequestUrl(requestUrl)
                .setRequestMethod(requestMethod)
                .setRequestHeaders(JsonUtils.toJson(requestHeaders))
                .setRequestBody(JsonUtils.toJson(requestBody))
                .setTaskStepSort(stepSort);
        taskStepDao.insert(taskStep);
        return taskStep;
    }
}
