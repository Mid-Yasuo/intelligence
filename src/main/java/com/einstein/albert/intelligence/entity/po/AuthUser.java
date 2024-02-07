package com.einstein.albert.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Data
@Accessors(chain = true)
@TableName("auth_user")
public class AuthUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    @TableField("name")
    private String name;

    /**
     * 账户
     */
    @TableField("username")
    private String username;

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * salt
     */
    @TableField("salt")
    private String salt;

    /**
     * 用户状态：0-未激活 1-正常 2-锁定 3-禁用 4-注销
     */
    @TableField("status")
    private Integer status;

    /**
     * 最近登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人 ID
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 最新修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @Version
    private Date updateTime;

    /**
     * 最新修改人 ID
     */
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private Long updater;


}
