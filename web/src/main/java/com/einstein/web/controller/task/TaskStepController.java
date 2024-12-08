package com.einstein.web.controller.task;

import com.einstein.database.entity.po.TaskStep;
import com.einstein.service.task.TaskStepService;
import com.einstein.web.entity.vo.Result;
import com.einstein.web.entity.vo.req.task.CreateTaskStepReq;
import com.einstein.web.entity.vo.resp.task.TaskStepResp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@RestController
@RequestMapping("/taskStep")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskStepController {

    private final TaskStepService taskStepService;

    @PostMapping("/add")
    public Result<TaskStepResp> addTaskStep(@RequestBody CreateTaskStepReq createTaskStepReq) {
        Long taskId = createTaskStepReq.getTaskId();
        String taskStepName = createTaskStepReq.getTaskStepName();
        String requestUrl = createTaskStepReq.getRequestUrl();
        String requestMethod = createTaskStepReq.getRequestMethod();
        Map<String, Object> requestHeaders = createTaskStepReq.getRequestHeaders();
        Map<String, Object> requestBody = createTaskStepReq.getRequestBody();
        Integer taskStepSort = createTaskStepReq.getTaskStepSort();
        TaskStep taskStep = taskStepService.createTaskStep(taskId, taskStepName, requestUrl, requestMethod, requestHeaders, requestBody,
                taskStepSort);
        return Result.success(TaskStepResp.build(taskStep));
    }
}
