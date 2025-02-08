package com.chunjiez.web.entity.vo.resp.task;

import com.chunjiez.common.constant.TaskStatus;
import com.chunjiez.database.entity.po.Task;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Data
@Accessors(chain = true)
public class TaskResp {

    private Long taskId;

    private String taskSn;

    private String taskName;

    private String logo;

    private String description;

    private String status;


    public static TaskResp build(Task task) {
        return new TaskResp()
                .setTaskId(task.getId())
                .setTaskSn(task.getTaskSn())
                .setTaskName(task.getName())
                .setLogo(task.getLogo())
                .setDescription(task.getDescription())
                .setStatus(TaskStatus.getReadableStatus(task.getStatus()));
    }
}
