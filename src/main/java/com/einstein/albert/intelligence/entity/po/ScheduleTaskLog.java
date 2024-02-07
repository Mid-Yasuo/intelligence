package com.einstein.albert.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务执行记录表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("schedule_task_log")
public class ScheduleTaskLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 任务名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 任务类
     */
    @TableField("task_class")
    private String taskClass;

    /**
     * 任务方法名
     */
    @TableField("task_method")
    private String taskMethod;


    /**
     * 任务方法参数类型列表
     */
    @TableField("task_param_types")
    private String taskParamTypes;

    /**
     * 任务方法参数列表
     */
    @TableField("task_params")
    private String taskParams;

    /**
     * 任务状态:0-进行中 1-已完成
     */
    @TableField("task_status")
    private Integer taskStatus;

    /**
     * 任务执行异常信息
     */
    @TableField("error_msg")
    private String errorMsg;

    @TableField("ts")
    private Long ts;

    /**
     * 执行日期 yyyyMMdd
     */
    @TableField("execute_date")
    private Integer executeDate;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
