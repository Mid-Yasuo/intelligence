package com.einstein.albert.intelligence.entity.vo.response;

import com.einstein.albert.intelligence.entity.po.Activity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/6
 */
@Data
@Accessors(chain = true)
public class ActivityVO {

    private Long id;

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


    public static ActivityVO transfer(Activity activity) {
        Date date = new Date();
        //活动状态：0-未开始 1-进行中 2-已结束 3-已取消
        int status = 3;
        if (date.before(activity.getStartTime())) {
            status = 0;
        } else if (date.after(activity.getStartTime()) && date.before(activity.getEndTime())) {
            status = 1;
        } else if (date.after(activity.getEndTime())) {
            status = 2;
        }
        return new ActivityVO()
                .setId(activity.getId())
                .setName((activity.getName()))
                .setDescription(activity.getDescription())
                .setStartTime(activity.getStartTime())
                .setEndTime(activity.getEndTime())
                .setStatus(status)
                .setMaxJoin(activity.getMaxJoin())
                .setMaxJoinRule(activity.getMaxJoinRule())
                .setMaxWin(activity.getMaxWin())
                .setConsumeAmount(activity.getConsumeAmount())
                .setConsumeIntegral(activity.getConsumeIntegral());
    }

}
