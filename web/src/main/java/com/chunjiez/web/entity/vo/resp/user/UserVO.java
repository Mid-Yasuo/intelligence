package com.chunjiez.web.entity.vo.resp.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.chunjiez.database.entity.po.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/11
 */
@Data
public class UserVO {

    /**
     * 主键 ID
     */
    private Long id;

    /**
     * 账号
     */
    private String username;

    /**
     * 头像链接
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机
     */
    @TableField("phone")
    private String phone;

    /**
     * 状态
     */
    @TableField("status")
    private Integer status;

    /**
     * 最新登录时间
     */
    @TableField("last_login_time")
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    public static UserVO build(User user) {
        UserVO vo = new UserVO();
        BeanUtils.copyProperties(user, vo);
        return vo;
    }
}
