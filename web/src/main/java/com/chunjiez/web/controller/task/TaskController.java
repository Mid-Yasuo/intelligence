//package com.chunjiez.web.controller.task;
//
//import com.chunjiez.database.entity.po.Task;
//import com.chunjiez.service.task.TaskExecuteService;
//import com.chunjiez.service.task.TaskService;
//import com.chunjiez.web.entity.vo.Result;
//import com.chunjiez.web.entity.vo.req.task.CreateTaskReq;
//import com.chunjiez.web.entity.vo.resp.task.TaskResp;
//import lombok.RequiredArgsConstructor;
//import org.apache.ibatis.annotations.Param;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @author 张春杰
// * @version 1.0.0
// * @date 2024/12/8
// */
//@RestController
//@RequestMapping("/task")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class TaskController {
//
//    private final TaskService taskService;
//
//    private final TaskExecuteService taskExecuteService;
//
//    @PostMapping("/create")
//    public Result<TaskResp> createTask(@RequestBody CreateTaskReq req) {
//        Task task = taskService.createTask(req.getName(), req.getLogo(), req.getDescription());
//        return Result.success(TaskResp.build(task));
//    }
//
//
//
//    @GetMapping("/execute")
//    public Result<String> execute(@Param("taskId") Long taskId) {
//        taskExecuteService.execute(taskId);
//        return Result.success();
//    }
//}
