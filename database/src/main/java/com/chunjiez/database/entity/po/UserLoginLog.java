package com.chunjiez.database.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户登录日志
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auth_user_login_log")
public class UserLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 账户
     */
    @TableField("username")
    private String username;

    /**
     * 登录IP
     */
    @TableField("login_ip")
    private String loginIp;

    /**
     * 登录时间
     */
    @TableField("login_datetime")
    private Date loginDatetime;

    /**
     * 登录状态 1-成功；0-失败
     */
    @TableField("login_status")
    private Integer loginStatus;

    @TableField("login_error_message")
    private String loginErrorMessage;

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


    public static UserLoginLog build(String username, String ip) {
       return new UserLoginLog().setUsername(username).setLoginIp(ip).setLoginDatetime(new Date());
    }
}
