package com.einstein.intelligence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.intelligence.dao.UserRoleDao;
import com.einstein.intelligence.entity.po.UserRole;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色关联表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

}
