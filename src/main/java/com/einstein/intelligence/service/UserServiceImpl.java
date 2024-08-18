package com.einstein.intelligence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.intelligence.dao.UserDao;
import com.einstein.intelligence.entity.po.User;
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

}
