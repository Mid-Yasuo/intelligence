package com.chunjiez.database.dao;

import com.chunjiez.database.entity.po.UserSalary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户薪资表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2025-02-08
 */
@Mapper
public interface UserSalaryDao extends BaseMapper<UserSalary> {

}
