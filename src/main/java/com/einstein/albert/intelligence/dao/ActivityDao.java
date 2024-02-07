package com.einstein.albert.intelligence.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.einstein.albert.intelligence.entity.po.Activity;
import com.einstein.albert.intelligence.entity.vo.PageParam;
import com.einstein.albert.intelligence.entity.vo.request.ActivitySearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Mapper
public interface ActivityDao extends BaseMapper<Activity> {

    /**
     * <p>获取活动列表</p>
     *
     * @param activitySearchRequest - [ActivitySearchRequest]
     * @return Page<Activity>
     * @author 张春杰
     * @date 2024/1/6
     */
    default Page<Activity> listPageData(ActivitySearchRequest activitySearchRequest) {
        PageParam pageParam = activitySearchRequest.getPageParam();
        Page<Activity> page = Page.of(pageParam.getPageNo(), pageParam.getPageSize());
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        String activityName = activitySearchRequest.getActivityName();
        String startTime = activitySearchRequest.getStartTime();
        String endTime = activitySearchRequest.getEndTime();
        queryWrapper.like(StringUtils.isNotBlank(activityName), "name", activityName)
                .ge(StringUtils.isNotBlank(startTime), "start_time", startTime)
                .le(StringUtils.isNotBlank(endTime), "end_time", endTime);
        return this.selectPage(page, queryWrapper);
    }

}
