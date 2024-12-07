package com.einstein.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.database.entity.po.RolePermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色与权限关联表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface RolePermissionDao extends BaseMapper<RolePermission> {

}
