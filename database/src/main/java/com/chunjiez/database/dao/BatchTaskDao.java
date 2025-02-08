package com.chunjiez.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chunjiez.database.entity.po.BatchTask;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 批量任务表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-08-18
 */
@Mapper
public interface BatchTaskDao extends BaseMapper<BatchTask> {

}
