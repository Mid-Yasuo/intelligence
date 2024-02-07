package com.einstein.albert.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 抽奖记录表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Data
@Accessors(chain = true)
@TableName("biz_lottery_record")
public class LotteryRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户 ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 活动 ID
     */
    @TableField("activity_id")
    private Long activityId;

    /**
     * 奖项 ID
     */
    @TableField("award_item_id")
    private Long awardItemId;

    /**
     * 是否中奖：0未中奖，1中奖
     */
    @TableField("winning_status")
    private Integer winningStatus;

    /**
     * 状态：0-未领奖 1-已领奖
     */
    @TableField("status")
    private Integer status;

    /**
     * 抽奖时间
     */
    @TableField("raffle_draw_time")
    private Date raffleDrawTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 创建人 ID
     */
    @TableField("creator")
    private Long creator;

    /**
     * 最新修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 最新修改人 ID
     */
    @TableField("updater")
    private Long updater;


}
