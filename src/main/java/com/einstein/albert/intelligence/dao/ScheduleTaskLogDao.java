package com.einstein.albert.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.albert.intelligence.entity.po.ScheduleTaskLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 定时任务执行记录表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-20
 */
@Mapper
public interface ScheduleTaskLogDao extends BaseMapper<ScheduleTaskLog> {

    /**
     * <p>查询指定日期最新的任务记录</p>
     *
     * @param taskName    - [String]
     * @param executeDate - [int]
     * @return ScheduleTaskLog
     * @author 张春杰
     * @date 2024/1/20
     */
    ScheduleTaskLog selectFirstByTaskName(@Param("taskName") String taskName,
                                          @Param("executeDate") int executeDate);
}
