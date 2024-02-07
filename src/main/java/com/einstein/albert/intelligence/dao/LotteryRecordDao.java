package com.einstein.albert.intelligence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.einstein.albert.intelligence.entity.po.LotteryRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 抽奖记录表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Mapper
public interface LotteryRecordDao extends BaseMapper<LotteryRecord> {

    /**
     * <p>count</p>
     *
     * @param userId     - [Long]
     * @param activityId - [Long]
     * @param start      - [String]
     * @param end        - [String]
     * @param status     - [Integer]
     * @return int
     * @author 张春杰
     * @date 2024/1/7
     */
    int countUserLotteryRecord(@Param("userId") Long userId, @Param("activityId") Long activityId, @Param("start") String start, @Param("end") String end, @Param("status") Integer status);
}
