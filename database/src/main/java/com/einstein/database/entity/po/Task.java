package com.einstein.database.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务序列
     */
    @TableField("task_sn")
    private String taskSn;

    /**
     * 任务名称
     */
    @TableField("name")
    private String name;

    /**
     * 任务图标
     */
    @TableField("logo")
    private String logo;

    /**
     * 任务描述
     */
    @TableField("description")
    private String description;

    /**
     * 客户 ID
     */
    @TableField("client_id")
    private Long clientId;

    /**
     * 状态 1-正常
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否删除：0-否 1-是
     */
    @TableField("deleted")
    @TableLogic
    private Integer deleted;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建用户
     */
    @TableField("create_by")
    private Long createBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 修改用户
     */
    @TableField("update_by")
    private Long updateBy;


}