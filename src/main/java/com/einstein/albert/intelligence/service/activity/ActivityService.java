package com.einstein.albert.intelligence.service.activity;

import com.einstein.albert.intelligence.entity.vo.ActivityDetailsRequest;
import com.einstein.albert.intelligence.entity.vo.PageResult;
import com.einstein.albert.intelligence.entity.vo.request.ActivitySearchRequest;
import com.einstein.albert.intelligence.entity.vo.request.LotteryDrawRequest;
import com.einstein.albert.intelligence.entity.vo.response.ActivityVO;
import com.einstein.albert.intelligence.entity.vo.response.AwardVO;

import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
public interface ActivityService {
    /**
     * <p>查询活动列表</p>
     *
     * @param activitySearchRequest - [ActivitySearchRequest]
     * @return PageResult<List < ActivityVO>>
     * @author 张春杰
     * @date 2024/1/6
     */
    PageResult<List<ActivityVO>> listActivities(ActivitySearchRequest activitySearchRequest);

    /**
     * <p>获取活动详情</p>
     *
     * @param activityId - [Long]
     * @return ActivityVO
     * @author 张春杰
     * @date 2024/1/7
     */
    ActivityVO getActivityDetails(Long activityId);

    /**
     * <p>新增活动</p>
     *
     * @param activityDetails - [ActivityDetailsRequest]
     * @author 张春杰
     * @date 2024/1/9
     */
    void addActivity(ActivityDetailsRequest activityDetails);

    /**
     * <p>抽奖</p>
     *
     * @param userId             - [long]
     * @param lotteryDrawRequest - [LotteryDrawRequest]
     * @return AwardVO
     * @author 张春杰
     * @date 2024/1/7
     */
    AwardVO lotteryDraw(long userId, LotteryDrawRequest lotteryDrawRequest);
}
