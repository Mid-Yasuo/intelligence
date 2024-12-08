package com.einstein.service.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.TaskScriptDao;
import com.einstein.database.entity.po.Task;
import com.einstein.database.entity.po.TaskScript;
import com.einstein.database.entity.po.TaskStep;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 任务脚本表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskScriptServiceImpl extends ServiceImpl<TaskScriptDao, TaskScript> implements TaskScriptService {

    private final TaskScriptDao taskScriptDao;

    private final TaskService taskService;

    private final TaskStepService taskStepService;

    @Override
    public List<TaskScript> listTaskStepScripts(Long taskId, Long taskStepId, String position) {
        return taskScriptDao.selectList(new LambdaQueryWrapper<TaskScript>().eq(TaskScript::getTaskId, taskId)
                .eq(TaskScript::getTaskStepId, taskStepId)
                .eq(StringUtils.isNotBlank(position), TaskScript::getScriptPosition, position));

    }

    @Override
    public TaskScript createTaskScript(Long taskId, Long taskStepId, String scriptPosition, String scriptOperation, String targetType,
                                       String targetKey, String targetValue) {
        Task task = taskService.getByTaskId(taskId);
        TaskStep taskStep = taskStepService.getByTaskStepId(taskStepId);
        TaskScript taskScript = new TaskScript().setTaskId(task.getId()).setTaskStepId(taskStep.getId())
                .setScriptPosition(scriptPosition)
                .setScriptOperation(scriptOperation)
                .setTargetType(targetType)
                .setTargetKey(targetKey)
                .setTargetValue(targetValue);
        taskScriptDao.insert(taskScript);
        return taskScript;
    }
}
