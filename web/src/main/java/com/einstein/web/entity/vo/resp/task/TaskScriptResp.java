package com.einstein.web.entity.vo.resp.task;

import com.einstein.database.entity.po.TaskScript;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
@Data
@Accessors(chain = true)
public class TaskScriptResp {

    private Long taskScriptId;

    /**
     * 执行时机： Before-请求前 After-请求后
     */
    private String scriptPosition;
    /**
     * 操作： get-获取参数 set-设置参数 download-下载 unzip-解压 zip-压缩文件
     */
    private String scriptOperation;

    private String targetKey;
    /**
     * 目标类型：variable |json | html | file
     */
    private String targetType;

    private String targetValue;


    public static TaskScriptResp build(TaskScript taskScript) {
        return new TaskScriptResp().setTaskScriptId(taskScript.getId())
                .setScriptPosition(taskScript.getScriptPosition())
                .setScriptOperation(taskScript.getScriptOperation())
                .setTargetType(taskScript.getTargetType())
                .setTargetKey(taskScript.getTargetKey())
                .setTargetValue(taskScript.getTargetValue());
    }
}
