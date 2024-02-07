package com.einstein.albert.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 定时任务表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-11
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("schedule_task")
public class ScheduleTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务名称
     */
    @TableField("task_name")
    private String taskName;

    /**
     * 任务描述
     */
    @TableField("task_description")
    private String taskDescription;

    /**
     * 任务CRON表达式
     */
    @TableField("task_cron")
    private String taskCron;

    /**
     * 任务全类名
     */
    @TableField("task_class")
    private String taskClass;

    /**
     * 任务方法名
     */
    @TableField("task_method")
    private String taskMethod;

    /**
     * 最新修改时间
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人 ID
     */
    @TableField("creator")
    private Long creator;

    /**
     * 最新修改时间
     */
    @TableField("update_time")
    @Version
    private Date updateTime;

    /**
     * 最新修改人 ID
     */
    @TableField("updater")
    private Long updater;


}
