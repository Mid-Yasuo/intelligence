package com.einstein.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.intelligence.entity.po.UserLoginLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户登录日志 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface UserLoginLogDao extends BaseMapper<UserLoginLog> {

}