package com.einstein.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.intelligence.entity.po.Permission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface PermissionDao extends BaseMapper<Permission> {

}
