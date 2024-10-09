package com.einstein.intelligence.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.intelligence.dao.UserDao;
import com.einstein.intelligence.entity.po.User;
import com.einstein.intelligence.entity.vo.PageParam;
import com.einstein.intelligence.entity.vo.PageResult;
import com.einstein.intelligence.entity.vo.req.user.GetUsersReq;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public IPage<User> getUsers(GetUsersReq getUsersReq) {
        PageParam pageParam = getUsersReq.getPageParam();
        return userDao.selectUsers(getUsersReq.getUsername(), pageParam.toMybatisPage());
    }
}
