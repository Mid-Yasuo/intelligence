package com.einstein.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.einstein.database.entity.po.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
public interface UserService extends IService<User> {

    IPage<User> getUsers(String username, Integer pageNo, Integer pageSize);
}
