package com.einstein.web.controller.task;

import com.einstein.database.entity.po.TaskScript;
import com.einstein.service.task.TaskScriptService;
import com.einstein.web.entity.vo.Result;
import com.einstein.web.entity.vo.req.task.CreateTaskScriptReq;
import com.einstein.web.entity.vo.resp.task.TaskScriptResp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@RestController
@RequestMapping("/taskScript")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TaskScriptController {

    private final TaskScriptService taskScriptService;

    @PostMapping("/add")
    public Result<TaskScriptResp> addTaskStep(@RequestBody @Validated CreateTaskScriptReq createTaskScriptReq) {
        Long taskId = createTaskScriptReq.getTaskId();
        long taskStepId = createTaskScriptReq.getTaskStepId();
        String scriptPosition = createTaskScriptReq.getScriptPosition();
        String scriptOperation = createTaskScriptReq.getScriptOperation();
        String targetType = createTaskScriptReq.getTargetType();
        String targetKey = createTaskScriptReq.getTargetKey();
        String targetValue = createTaskScriptReq.getTargetValue();
        TaskScript taskScript = taskScriptService.createTaskScript(taskId, taskStepId, scriptPosition, scriptOperation, targetType, targetKey,
                targetValue);
        return Result.success(TaskScriptResp.build(taskScript));
    }
}
