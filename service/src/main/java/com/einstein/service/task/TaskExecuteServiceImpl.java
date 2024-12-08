package com.einstein.service.task;

import com.dtflys.forest.http.ForestResponse;
import com.einstein.common.constant.*;
import com.einstein.common.entity.exception.BusinessException;
import com.einstein.common.entity.exception.SystemException;
import com.einstein.common.util.CollectionUtils;
import com.einstein.common.util.FileUtils;
import com.einstein.common.util.HttpClientUtils;
import com.einstein.common.util.JsonUtils;
import com.einstein.database.entity.po.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskExecuteServiceImpl implements TaskExecuteService {

    private final TaskService taskService;

    private final TaskLogService taskLogService;

    private final TaskStepService taskStepService;

    private final TaskStepLogService taskStepLogService;

    private final TaskScriptService taskScriptService;

    private final TaskVariableService taskVariableService;

    @Override
    public void execute(Long taskId) {
        Task task = taskService.getByTaskId(taskId);
        List<TaskStep> taskSteps = taskStepService.listByTaskId(taskId);
        if (CollectionUtils.isEmpty(taskSteps)) {
            log.warn("task step of task [{}] is empty", task.getName());
            return;
        }
        TaskLog inProcessTaskLog = taskLogService.getTaskLog(taskId, TaskExecuteStatusEnum.IN_PROCESS.getCode());
        if (Objects.nonNull(inProcessTaskLog)) {
            throw new BusinessException(BusinessEnum.TASK_IN_PROCESS);
        }
        TaskLog taskLog = taskLogService.createTaskExecuteLog(taskId);
        if (taskLog == null) {
            throw new SystemException("create task execute log error.");
        }
        try {
            for (TaskStep taskStep : taskSteps) {
                Long taskStepId = taskStep.getId();
                executeRequest(taskLog, taskStep, getTaskStepScripts(taskId, taskStepId, ScriptPositionEnum.Before),
                        getTaskStepScripts(taskId, taskStepId, ScriptPositionEnum.After));
            }
            taskLog.setStatus(TaskExecuteStatusEnum.SUCCESS.getCode());
        } catch (Exception exception) {
            log.error("exception:", exception);
            taskLog.setStatus(TaskExecuteStatusEnum.ERROR.getCode());
        } finally {
            Date endTime = new Date();
            long ts = endTime.getTime() - taskLog.getStartTime().getTime();
            taskLogService.updateById(taskLog.setEndTime(endTime).setTs(ts));
        }
    }

    @SuppressWarnings("unchecked")
    public void executeRequest(TaskLog taskLog, TaskStep taskStep, List<TaskScript> beforeScripts, List<TaskScript> afterScripts) {
        String requestUrl = taskStep.getRequestUrl();
        String requestMethod = taskStep.getRequestMethod();
        String requestHeaders = taskStep.getRequestHeaders();
        Map<String, String> headerMap = JsonUtils.toJavaBean(requestHeaders, Map.class);
        String requestBody = taskStep.getRequestBody();
        Long taskId = taskStep.getTaskId();
        Long taskStepId = taskStep.getId();
        TaskStepLog taskStepLog = TaskStepLog.build(taskLog.getId(), taskId, taskStepId, requestUrl, requestMethod,
                requestHeaders, requestBody);
        taskStepLogService.save(taskStepLog);
        Long taskStepLogId = taskStepLog.getId();
        try {
            ForestResponse<?> response = HttpClientUtils.request(HttpMethod.valueOf(requestMethod), requestUrl, headerMap, requestBody);
            long contentLength = response.getContentLength();
            if (contentLength < 0 || contentLength > Constant.TEXT_MAX_LENGTH) {
                String filePath = FileUtils.write(taskId + Constant.FILE_SEPARATOR + taskStepId + Constant.FILE_SEPARATOR + taskStepLogId,
                        response.getInputStream());
                taskStepLog.setResponseFile(filePath);
            }
            taskStepLog.setResponseStatus(response.getStatusCode())
                    .setResponseHeaders(JsonUtils.toJson(response.getHeaders()))
                    .setResponseBody(Objects.isNull(taskStepLog.getResponseFile()) ? response.getContent() : null)
                    .setStatus(response.isSuccess() ? TaskExecuteStatusEnum.SUCCESS.getCode() : TaskExecuteStatusEnum.ERROR.getCode());
        } catch (Exception exception) {
            log.error("execute request error.", exception);
            taskStepLog.setStatus(TaskExecuteStatusEnum.ERROR.getCode()).setMessage(exception.getMessage());
        } finally {
            taskStepLogService.updateById(taskStepLog);
        }
    }

    /**
     * 获取任务执行脚本
     *
     * @param taskId
     * @param taskStepId
     * @return
     */
    public List<TaskScript> getTaskStepScripts(Long taskId, Long taskStepId, ScriptPositionEnum position) {
        return taskScriptService.listTaskStepScripts(taskId, taskStepId, position == null ? null : position.name());
    }
}
