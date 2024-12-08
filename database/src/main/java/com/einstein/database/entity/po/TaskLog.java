package com.einstein.database.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 任务日志表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("task_log")
public class TaskLog implements Serializable {

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
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 耗时
     */
    @TableField("ts")
    private Long ts;

    /**
     * 状态 0- 进行中 1-成功 2-失败
     */
    @TableField("status")
    private Integer status;

    /**
     * 客户 ID
     */
    @TableField(value = "client_id",fill = FieldFill.INSERT)
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
