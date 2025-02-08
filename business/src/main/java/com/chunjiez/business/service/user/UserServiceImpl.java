package com.chunjiez.business.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunjiez.database.dao.UserDao;
import com.chunjiez.database.entity.po.User;
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
    public IPage<User> listUsers(String username, Integer pageNo, Integer pageSize) {
        return userDao.selectUsers(username, new Page<>(pageNo, pageSize));
    }

    @Override
    public User getByUsername(String username) {
        return userDao.selectByUsername(username);

    }
}
