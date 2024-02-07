package com.einstein.albert.intelligence.service;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
public interface LotteryRecordService {
    /**
     * <p>统计抽奖记录数</p>
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
    int countUserLotteryRecord(Long userId, Long activityId, String start, String end, Integer status);
}
