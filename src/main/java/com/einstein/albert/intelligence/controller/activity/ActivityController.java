package com.einstein.albert.intelligence.controller.activity;

import com.einstein.albert.intelligence.common.ContentHolder;
import com.einstein.albert.intelligence.entity.TokenCache;
import com.einstein.albert.intelligence.entity.annotation.RequireAuthentication;
import com.einstein.albert.intelligence.entity.vo.ActivityDetailsRequest;
import com.einstein.albert.intelligence.entity.vo.PageResult;
import com.einstein.albert.intelligence.entity.vo.Result;
import com.einstein.albert.intelligence.entity.vo.request.ActivitySearchRequest;
import com.einstein.albert.intelligence.entity.vo.request.LotteryDrawRequest;
import com.einstein.albert.intelligence.entity.vo.response.ActivityVO;
import com.einstein.albert.intelligence.service.activity.ActivityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@RestController
@RequestMapping("/activity/v1.0")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }


    @PostMapping("/list")
    public PageResult<List<ActivityVO>> listActivities(@RequestBody ActivitySearchRequest activitySearchRequest) {
        return activityService.listActivities(activitySearchRequest);
    }

    @PostMapping("/createActivity")
    public Result<?> createActivity(@RequestBody ActivityDetailsRequest activityDetails) {
        activityService.addActivity(activityDetails);
        return Result.success();
    }

    @PostMapping("/drawLottery")
    @RequireAuthentication
    public Result<?> drawLottery(LotteryDrawRequest lotteryDrawRequest) {
        TokenCache tokenCache = ContentHolder.getUserTokenCache();
        return Result.success(activityService.lotteryDraw(tokenCache.getUserId(), lotteryDrawRequest));
    }
}
