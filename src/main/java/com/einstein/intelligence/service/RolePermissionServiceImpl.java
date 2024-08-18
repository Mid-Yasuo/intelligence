package com.einstein.intelligence.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.intelligence.dao.RolePermissionDao;
import com.einstein.intelligence.entity.po.RolePermission;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与权限关联表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionDao, RolePermission> implements RolePermissionService {

}
