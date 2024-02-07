package com.einstein.albert.intelligence.entity.vo;

import com.einstein.albert.intelligence.entity.vo.request.AwardItemReq;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/9
 */
@Data
public class ActivityDetailsRequest {

    private String name;

    private String description;

    private Date startDate;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    private Integer status;

    private Integer maxJoin;

    private Integer maxJoinRule;

    private Integer maxWin;

    /**
     * 消费金额
     */
    private BigDecimal consumeAmount;

    /**
     * 消费积分
     */
    private Integer consumeIntegral;

    @Valid
    @NotEmpty(message = "奖项列表为空")
    private List<AwardItemReq> awardItems;
}
