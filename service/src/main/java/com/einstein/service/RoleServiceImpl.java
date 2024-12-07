package com.einstein.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.RoleDao;
import com.einstein.database.entity.po.Role;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

}