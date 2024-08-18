package com.einstein.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.intelligence.entity.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface UserDao extends BaseMapper<User> {

    /**
     * 根据账户名称查询
     * @param username
     * @return
     */
    User selectByUsername(@Param("username") String username);
}
