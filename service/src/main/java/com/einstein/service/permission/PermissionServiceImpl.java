package com.einstein.service.permission;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.einstein.database.dao.PermissionDao;
import com.einstein.database.entity.po.Permission;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDao, Permission> implements PermissionService {

}