package com.chunjiez.database.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 批量任务表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("batch_task")
public class BatchTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 任务名称
     */
    @TableField("name")
    private String name;

    /**
     * 任务类型
     */
    @TableField("type")
    private String type;

    /**
     * 任务总数
     */
    @TableField("total")
    private Integer total;

    /**
     * 执行任务数
     */
    @TableField("process")
    private Integer process;

    /**
     * 成功数
     */
    @TableField("success")
    private Integer success;

    /**
     * 资源链接
     */
    @TableField("resource_href")
    private String resourceHref;

    /**
     * 状态 0-执行中 1-执行成功 2-执行失败
     */
    @TableField("status")
    private Integer status;

    @TableField("client_id")
    private String clientId;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
