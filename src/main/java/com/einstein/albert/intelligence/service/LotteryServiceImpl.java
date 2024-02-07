package com.einstein.albert.intelligence.service;

import com.einstein.albert.intelligence.dao.LotteryRecordDao;
import com.einstein.albert.intelligence.entity.po.Award;
import com.einstein.albert.intelligence.entity.po.AwardItem;
import com.einstein.albert.intelligence.entity.po.UserWallet;
import com.einstein.albert.intelligence.entity.vo.response.ActivityVO;
import com.einstein.albert.intelligence.service.activity.ActivityService;
import com.einstein.albert.intelligence.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/7
 */
@Slf4j
@Service
public class LotteryServiceImpl implements LotteryService {

    private ActivityService activityService;

    private UserWalletService userWalletService;

    private LotteryRecordDao lotteryRecordDao;

    @Autowired
    public void setActivityService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Autowired
    public void setUserWalletService(UserWalletService userWalletService) {
        this.userWalletService = userWalletService;
    }

    @Autowired
    public void setLotteryRecordDao(LotteryRecordDao lotteryRecordDao) {
        this.lotteryRecordDao = lotteryRecordDao;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Award doLotteryDraw(Long userId, ActivityVO activity) {
        UserWallet userWallet = userWalletService.getUserWallet(userId);
        BigDecimal consumeAmount = activity.getConsumeAmount();
        Integer consumeIntegral = activity.getConsumeIntegral();
        BigDecimal balanceAmount = userWallet.getBalanceAmount();
        Long integral = userWallet.getIntegral();

        return null;
    }

    /**
     * <p>初始化奖池</p>
     *
     * @param awards - [ActivityAward>]
     * @return int
     * @author 张春杰
     * @date 2023/8/29
     */
    private AwardItem initJackpot(List<AwardItem> awards) {
        int awardSize = awards.size();
        int[] jackpot = new int[awardSize + 1];
        int probabilityBound = 0;
        int index = 0;
        for (AwardItem award : awards) {
            probabilityBound += award.getProbability()
                    .multiply(BigDecimal.valueOf(10000))
                    .intValue();
            jackpot[index] = probabilityBound;
            index++;
        }
        int emptyProbability = 10000;
        //设置空奖概率
        jackpot[index] = emptyProbability;
        log.info("jackpot:{}", Arrays.toString(jackpot));
        int randomNo = RandomUtils.randomNo(probabilityBound);
        log.info("randomNo:{}", randomNo);
        int start = 0;
        for (int i = 0; i < jackpot.length; i++) {
            if (randomNo > start && randomNo <= jackpot[i]) {
                return awards.get(i);
            }
            start = jackpot[i];
        }
        return null;
    }
}
