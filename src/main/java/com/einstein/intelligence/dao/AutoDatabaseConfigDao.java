package com.einstein.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.intelligence.entity.po.AutoDatabaseConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据库配置表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface AutoDatabaseConfigDao extends BaseMapper<AutoDatabaseConfig> {

}
