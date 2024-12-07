package com.einstein.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.database.entity.po.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface RoleDao extends BaseMapper<Role> {

}
