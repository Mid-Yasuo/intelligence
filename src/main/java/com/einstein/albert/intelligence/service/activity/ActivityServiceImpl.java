package com.einstein.albert.intelligence.service.activity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einstein.albert.intelligence.common.ContentHolder;
import com.einstein.albert.intelligence.dao.ActivityDao;
import com.einstein.albert.intelligence.entity.annotation.DistributedLock;
import com.einstein.albert.intelligence.entity.constant.BusinessEnum;
import com.einstein.albert.intelligence.entity.constant.CommonConst;
import com.einstein.albert.intelligence.entity.po.Activity;
import com.einstein.albert.intelligence.entity.vo.ActivityDetailsRequest;
import com.einstein.albert.intelligence.entity.vo.PageParam;
import com.einstein.albert.intelligence.entity.vo.PageResult;
import com.einstein.albert.intelligence.entity.vo.request.ActivitySearchRequest;
import com.einstein.albert.intelligence.entity.vo.request.LotteryDrawRequest;
import com.einstein.albert.intelligence.entity.vo.response.ActivityVO;
import com.einstein.albert.intelligence.entity.vo.response.AwardVO;
import com.einstein.albert.intelligence.exception.BusinessException;
import com.einstein.albert.intelligence.service.LotteryRecordService;
import com.einstein.albert.intelligence.service.LotteryService;
import com.einstein.albert.intelligence.service.award.AwardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Slf4j
@Service
public class ActivityServiceImpl implements ActivityService {

    private final ActivityDao activityDao;

    private AwardService awardService;

    private AwardItemService awardItemService;

    private LotteryService lotteryService;

    private LotteryRecordService lotteryRecordService;

    public ActivityServiceImpl(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    @Autowired
    public void setAwardService(AwardService awardService) {
        this.awardService = awardService;
    }

    @Autowired
    public void setAwardItemService(AwardItemService awardItemService) {
        this.awardItemService = awardItemService;
    }

    @Autowired
    public void setLotteryService(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @Autowired
    public void setLotteryRecordService(LotteryRecordService lotteryRecordService) {
        this.lotteryRecordService = lotteryRecordService;
    }

    @Override
    public PageResult<List<ActivityVO>> listActivities(ActivitySearchRequest activitySearchRequest) {
        Page<Activity> pageData = activityDao.listPageData(activitySearchRequest);
        PageParam pageParam = activitySearchRequest.getPageParam();
        List<ActivityVO> vos = pageData.getRecords().stream()
                .map(ActivityVO::transfer)
                .collect(Collectors.toList());
        return new PageResult<>((int) pageData.getTotal(), pageParam.getPageNo(), pageParam.getPageSize(), vos);
    }

    @Override
    public ActivityVO getActivityDetails(Long activityId) {
        Activity activity = activityDao.selectById(activityId);
        if (activity == null) {
            throw new BusinessException(BusinessEnum.ACTIVITY_NOT_FOUND);
        }
        return ActivityVO.transfer(activity);
    }

    @Override
    @DistributedLock(keyPrefix = "addActivity", keyValue = "#activityDetails.name")
    public void addActivity(ActivityDetailsRequest activityDetails) {

    }

    @Override
    @DistributedLock(keyPrefix = "lotteryDraw", keyValue = "#userId")
    public AwardVO lotteryDraw(long userId, LotteryDrawRequest lotteryDrawRequest) {
        ActivityVO details = getActivityDetails(lotteryDrawRequest.getActivityId());
        boolean drawLottery = isDrawLottery(userId, details);
        if (drawLottery) {

        }
        lotteryService.doLotteryDraw(ContentHolder.getUserTokenCache().getUserId(), details);
        return null;
    }

    private boolean isDrawLottery(Long userId, ActivityVO activity) {
        Integer maxJoin = activity.getMaxJoin();
        log.info("判断是否进行抽奖，活动允许最大抽奖次数【{}】", maxJoin);
        if (Objects.nonNull(maxJoin) && maxJoin > 0) {
            Integer maxJoinRule = activity.getMaxJoinRule();
            int usedJoinCount = getUsedJoinCount(userId, activity.getId(), maxJoinRule);
            if (usedJoinCount >= maxJoin) {
                return false;
            }
        }
        return true;
    }

    private int getUsedJoinCount(Long userId, Long activityId, Integer maxJoinRule) {
        boolean countDaily = 1 == maxJoinRule;
        if (countDaily) {
            LocalDate today = LocalDate.now();
            LocalDateTime nextDay = today.plusDays(1).atStartOfDay();
            String start = today.atStartOfDay().format(DateTimeFormatter.ofPattern(CommonConst.DATE_FORMAT_PATTERN));
            String end = nextDay.format(DateTimeFormatter.ofPattern(CommonConst.DATE_FORMAT_PATTERN));
            return lotteryRecordService.countUserLotteryRecord(userId, activityId, start, end, null);
        }
        return lotteryRecordService.countUserLotteryRecord(userId, activityId, null, null, null);
    }
}
