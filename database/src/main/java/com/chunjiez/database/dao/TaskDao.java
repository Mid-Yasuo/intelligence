package com.chunjiez.database.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chunjiez.database.entity.po.Task;
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

    /**
     * 根据名称查询任务
     *
     * @param taskName
     * @return
     */
    default Task selectByName(String taskName) {
        return this.selectOne(new LambdaQueryWrapper<Task>().eq(Task::getName, taskName).last(" LIMIT 1"));
    }
}
