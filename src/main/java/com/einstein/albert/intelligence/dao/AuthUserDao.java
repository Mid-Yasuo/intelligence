package com.einstein.albert.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.albert.intelligence.entity.po.AuthUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Mapper
public interface AuthUserDao extends BaseMapper<AuthUser> {

    AuthUser selectByUsername(String username);
}
