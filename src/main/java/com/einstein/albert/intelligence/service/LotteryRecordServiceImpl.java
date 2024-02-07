package com.einstein.albert.intelligence.service;

import com.einstein.albert.intelligence.dao.LotteryRecordDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Slf4j
@Service
public class LotteryRecordServiceImpl implements LotteryRecordService {

    private final LotteryRecordDao lotteryRecordDao;

    public LotteryRecordServiceImpl(LotteryRecordDao lotteryRecordDao) {
        this.lotteryRecordDao = lotteryRecordDao;
    }

    @Override
    public int countUserLotteryRecord(Long userId, Long activityId, String start, String end, Integer status) {
        return lotteryRecordDao.countUserLotteryRecord(userId, activityId, start, end, status);
    }
}
