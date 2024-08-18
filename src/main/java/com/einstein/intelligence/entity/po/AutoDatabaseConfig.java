package com.einstein.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 数据库配置表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("auto_database_config")
public class AutoDatabaseConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField("host")
    private String host;

    @TableField("port")
    private Integer port;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("database")
    private String database;

    @TableField("status")
    private Integer status;

    @TableField("client_id")
    private String clientId;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;


}
