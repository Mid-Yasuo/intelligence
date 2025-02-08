package com.chunjiez.business.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chunjiez.database.entity.po.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
public interface UserService extends IService<User> {

    /**
     * 查询用户例表
     * @param username
     * @param pageNo
     * @param pageSize
     * @return
     */
    IPage<User> listUsers(String username, Integer pageNo, Integer pageSize);

    /**
     * 根据username 获取用户信息
     * @param username
     * @return
     */
    User getByUsername(String username);
}
