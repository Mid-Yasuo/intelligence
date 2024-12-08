package com.einstein.database.dao;

import com.einstein.database.entity.po.TaskLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 任务日志表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Mapper
public interface TaskLogDao extends BaseMapper<TaskLog> {

}
