package com.einstein.database.entity.po;

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
 * 
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("task_step_log")
public class TaskStepLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("task_log_id")
    private Long taskLogId;

    @TableField("task_id")
    private Long taskId;

    @TableField("task_step_id")
    private Long taskStepId;

    @TableField("start_time")
    private Date startTime;

    @TableField("end_time")
    private Date endTime;

    @TableField("request_url")
    private String requestUrl;

    @TableField("request_method")
    private String requestMethod;

    @TableField("request_headers")
    private String requestHeaders;

    @TableField("request_cookies")
    private String requestCookies;

    @TableField("request_body")
    private String requestBody;

    @TableField("response_status")
    private Integer responseStatus;

    @TableField("response_headers")
    private String responseHeaders;

    @TableField("response_body")
    private String responseBody;

    @TableField("ts")
    private Long ts;

    @TableField("status")
    private Integer status;

    @TableField("message")
    private String message;

    @TableField("client_id")
    private Long clientId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
