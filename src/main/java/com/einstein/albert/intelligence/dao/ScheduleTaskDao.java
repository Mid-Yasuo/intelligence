package com.einstein.albert.intelligence.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.albert.intelligence.entity.po.ScheduleTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 定时任务表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-11
 */
@Mapper
public interface ScheduleTaskDao extends BaseMapper<ScheduleTask> {

    /**
     * <p>查询定时任务列表</p>
     *
     * @param status - [int]
     * @return List<ScheduleTask>
     * @author 张春杰
     * @date 2024/1/11
     */
    default List<ScheduleTask> list(int status) {
        QueryWrapper<ScheduleTask> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", status);
        return this.selectList(queryWrapper);
    }
}
