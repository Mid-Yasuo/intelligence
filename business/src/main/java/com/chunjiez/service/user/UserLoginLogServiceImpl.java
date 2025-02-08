package com.chunjiez.service.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chunjiez.database.dao.UserLoginLogDao;
import com.chunjiez.database.entity.po.UserLoginLog;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户登录日志 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class UserLoginLogServiceImpl extends ServiceImpl<UserLoginLogDao, UserLoginLog> implements UserLoginLogService {

}
