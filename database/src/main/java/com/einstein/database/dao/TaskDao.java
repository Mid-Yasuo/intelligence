package com.einstein.database.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.database.entity.po.Task;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 任务表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Mapper
public interface TaskDao extends BaseMapper<Task> {

}
