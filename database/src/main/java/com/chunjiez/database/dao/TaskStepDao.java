package com.chunjiez.database.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chunjiez.database.entity.po.TaskStep;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 任务步骤表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-12-07
 */
@Mapper
public interface TaskStepDao extends BaseMapper<TaskStep> {

    /**
     * 查询任务步骤列表
     *
     * @param taskId
     * @return
     */
    default List<TaskStep> listByTaskId(Long taskId) {
        return this.selectList(new LambdaQueryWrapper<TaskStep>().eq(TaskStep::getTaskId, taskId).orderByAsc(TaskStep::getTaskStepSort));
    }

    /**
     * 根据步骤名称查询步骤
     *
     * @param taskId
     * @param taskStepName
     * @return
     */
    default TaskStep selectByStepName(Long taskId, String taskStepName) {
        return this.selectOne(new LambdaQueryWrapper<TaskStep>().eq(TaskStep::getTaskId, taskId).eq(TaskStep::getTaskStepName,
                taskStepName).last(" LIMIT 1"));
    }

}
