package com.einstein.albert.intelligence.task;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einstein.albert.intelligence.dao.ActivityDao;
import com.einstein.albert.intelligence.entity.annotation.GetExecutionTime;
import com.einstein.albert.intelligence.entity.annotation.TaskAnnotation;
import com.einstein.albert.intelligence.entity.constant.ActivityStatusEnum;
import com.einstein.albert.intelligence.entity.constant.CommonConst;
import com.einstein.albert.intelligence.entity.po.Activity;
import com.einstein.albert.intelligence.entity.vo.PageParam;
import com.einstein.albert.intelligence.entity.vo.request.ActivitySearchRequest;
import com.einstein.albert.intelligence.util.BatchTaskUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/20
 */
@Component
public class TaskJob {

    private ActivityDao activityDao;

    @Autowired
    public void setActivityDao(ActivityDao activityDao) {
        this.activityDao = activityDao;
    }

    /**
     * <p>批量更新活动状态</p>
     *
     * @param pageNo   - [int]
     * @param pageSize - [int]
     * @return boolean true 继续更新 false 更新完成
     * @author 张春杰
     * @date 2024/1/20
     */
    @TaskAnnotation(name = "批量更新活动状态")
    @GetExecutionTime
    public boolean batchRefresh(int pageNo, int pageSize) {
        LocalDateTime now = LocalDateTime.now();
        ActivitySearchRequest searchRequest = new ActivitySearchRequest()
                .setReleaseStatus(CommonConst.Status.Y);
        searchRequest.setPageParam(new PageParam().setPageNo(pageNo).setPageSize(pageSize));
        Page<Activity> pageData = activityDao.listPageData(searchRequest);
        List<Activity> records = pageData.getRecords();
        if (records.isEmpty()) {
            return false;
        }
        BatchTaskUtils.batchRun(records, 5, activity -> {
            LocalDateTime start = LocalDateTime.ofInstant(activity.getStartTime().toInstant(), ZoneId.systemDefault());
            LocalDateTime end = LocalDateTime.ofInstant(activity.getEndTime().toInstant(), ZoneId.systemDefault());
            if (start.isAfter(now) && ActivityStatusEnum.NOT_STARTED.getCode() == activity.getStatus()) {
                activity.setStatus(ActivityStatusEnum.STARTED.getCode());
            }
            if (now.isAfter(end) && ActivityStatusEnum.STARTED.getCode() == activity.getStatus()) {
                activity.setStatus(ActivityStatusEnum.ENDED.getCode());
            }
            activityDao.updateById(activity);
            return null;
        });
        return records.size() >= pageSize;
    }
}
