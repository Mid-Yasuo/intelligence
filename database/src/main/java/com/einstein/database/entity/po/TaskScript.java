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
     * 执行时机： before-请求前 after-请求后
     */
    @TableField("script_condition")
    private String scriptCondition;

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
     * 源 Key
     */
    @TableField("source_key")
    private String sourceKey;

    /**
     * 目标 Key
     */
    @TableField("target_key")
    private String targetKey;

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
