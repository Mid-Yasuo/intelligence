package com.einstein.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * 权限表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    @TableField("name")
    private String name;

    /**
     * 权限码
     */
    @TableField("resource")
    private String resource;

    /**
     * 权限路由
     */
    @TableField("route")
    private String route;

    /**
     * 权限类型：1-目录 2-菜单 3-页面 4-按钮
     */
    @TableField("type")
    private Integer type;

    /**
     * 状态：1-正常
     */
    @TableField("status")
    private Integer status;

    /**
     * 是否删除 0-否 1-是
     */
    @TableField("deleted")
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
    private String createBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 修改用户
     */
    @TableField("update_by")
    private String updateBy;


}
