package com.einstein.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.einstein.intelligence.entity.annotation.GetExecutionTime;
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
     *
     * @param username
     * @return
     */
    @GetExecutionTime
    User selectByUsername(@Param("username") String username);

    /**
     * 查询用户列表
     *
     * @param username
     * @param page
     * @return
     */
    @GetExecutionTime
    IPage<User> selectUsers(@Param("username") String username, @Param("page") IPage<User> page);
}
