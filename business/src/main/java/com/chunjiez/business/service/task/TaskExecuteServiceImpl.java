package com.chunjiez.business.service.task;

import com.dtflys.forest.http.ForestResponse;
import com.chunjiez.common.constant.BusinessCode;
import com.chunjiez.common.constant.Constant;
import com.chunjiez.common.constant.HttpMethod;
import com.chunjiez.common.constant.TaskExecuteStatus;
import com.chunjiez.common.entity.exception.BusinessException;
import com.chunjiez.common.entity.exception.SystemException;
import com.chunjiez.common.util.CollectionUtils;
import com.chunjiez.common.util.FileUtils;
import com.chunjiez.common.util.HttpClientUtils;
import com.chunjiez.common.util.JsonUtils;
import com.chunjiez.database.entity.po.Task;
import com.chunjiez.database.entity.po.TaskLog;
import com.chunjiez.database.entity.po.TaskStep;
import com.chunjiez.database.entity.po.TaskStepLog;
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

    @Override
    public void execute(Long taskId) {
        Task task = taskService.getByTaskId(taskId);
        List<TaskStep> taskSteps = taskStepService.listByTaskId(taskId);
        if (CollectionUtils.isEmpty(taskSteps)) {
            log.warn("task step of task [{}] is empty", task.getName());
            return;
        }
        TaskLog inProcessTaskLog = taskLogService.getTaskLog(taskId, TaskExecuteStatus.IN_PROCESS.getStatus());
        if (Objects.nonNull(inProcessTaskLog)) {
            throw new BusinessException(BusinessCode.TASK_IN_PROCESS);
        }
        TaskLog taskLog = taskLogService.createTaskExecuteLog(taskId);
        if (taskLog == null) {
            throw new SystemException("create task execute log error.");
        }
        try {
            for (TaskStep taskStep : taskSteps) {
                executeRequest(taskLog, taskStep);
            }
            taskLog.setStatus(TaskExecuteStatus.SUCCESS.getStatus());
        } catch (Exception exception) {
            log.error("exception:", exception);
            taskLog.setStatus(TaskExecuteStatus.ERROR.getStatus());
        } finally {
            Date endTime = new Date();
            long ts = endTime.getTime() - taskLog.getStartTime().getTime();
            taskLogService.updateById(taskLog.setEndTime(endTime).setTs(ts));
        }
    }

    @SuppressWarnings("unchecked")
    public void executeRequest(TaskLog taskLog, TaskStep taskStep) {
        Long taskId = taskStep.getTaskId();
        Long taskStepId = taskStep.getId();
        String requestUrl = taskStep.getRequestUrl();
        String requestMethod = taskStep.getRequestMethod();
        String requestHeaders = taskStep.getRequestHeaders();
        Map<String, String> headerMap = JsonUtils.toJavaBean(requestHeaders, Map.class);
        String requestBody = taskStep.getRequestBody();
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
                    .setStatus(response.isSuccess() ? TaskExecuteStatus.SUCCESS.getStatus() : TaskExecuteStatus.ERROR.getStatus());
        } catch (Exception exception) {
            log.error("execute request error.", exception);
            taskStepLog.setStatus(TaskExecuteStatus.ERROR.getStatus()).setMessage(exception.getMessage());
        } finally {
            taskStepLogService.updateById(taskStepLog);
        }
    }
}
