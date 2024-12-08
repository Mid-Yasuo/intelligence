package com.einstein.database.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 任务脚本表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("task_script")
public class TaskScript implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务 ID
     */
    @TableField("task_id")
    private Long taskId;

    /**
     * 任务步骤 ID
     */
    @TableField("task_step_id")
    private Long taskStepId;

    /**
     * 执行时机： Before-请求前 After-请求后
     */
    @TableField("script_position")
    private String scriptPosition;

    /**
     * 操作： get-获取参数 set-设置参数 download-下载 unzip-解压 zip-压缩文件
     */
    @TableField("script_operation")
    private String scriptOperation;

    /**
     * 1
     */
    @TableField("scope")
    private Integer scope;

    /**
     * 目标类型：json | html | file
     */
    @TableField("target_type")
    private String targetType;

    /**
     * 目标 Key
     */
    @TableField("target_key")
    private String targetKey;
    /**
     * 目标 Key
     */
    @TableField("target_value")
    private String targetValue;

    /**
     * 客户 ID
     */
    @TableField(value = "client_id", fill = FieldFill.INSERT)
    private String clientId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建用户
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 修改用户
     */
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;


}
