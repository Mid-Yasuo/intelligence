package com.chunjiez.database.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 任务步骤表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("task_step")
public class TaskStep implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务步骤名称
     */
    @TableField("task_step_name")
    private String taskStepName;

    /**
     * 请求地址
     */
    @TableField("request_url")
    private String requestUrl;

    /**
     * 请求方式
     */
    @TableField("request_method")
    private String requestMethod;

    /**
     * 请求头
     */
    @TableField("request_headers")
    private String requestHeaders;

    /**
     * 请求体
     */
    @TableField("request_body")
    private String requestBody;

    /**
     * 任务步骤序号
     */
    @TableField("task_step_sort")
    private Integer taskStepSort;

    /**
     * 任务 ID
     */
    @TableField("task_id")
    private Long taskId;

    /**
     * 客户 ID
     */
    @TableField("client_id")
    private String clientId;

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
