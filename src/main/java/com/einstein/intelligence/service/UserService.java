package com.einstein.intelligence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.einstein.intelligence.entity.po.User;
import com.einstein.intelligence.entity.vo.req.user.GetUsersReq;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
public interface UserService extends IService<User> {

    IPage<User> getUsers(GetUsersReq getUsersReq);
}
