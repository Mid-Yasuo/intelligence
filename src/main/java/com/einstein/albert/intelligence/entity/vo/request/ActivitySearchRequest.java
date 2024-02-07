package com.einstein.albert.intelligence.entity.vo.request;

import com.einstein.albert.intelligence.entity.vo.PageParam;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Data
@Accessors(chain = true)
public class ActivitySearchRequest {

    private String activityName;

    private String startTime;

    private String endTime;

    private Integer releaseStatus;

    @Valid
    @NotNull(message = "page param is null")
    private PageParam pageParam;
}
