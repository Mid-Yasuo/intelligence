package com.einstein.albert.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Data
@Accessors(chain = true)
@TableName("biz_activity")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("activity_sn")
    private String activitySn;

    /**
     * 活动名称
     */
    @TableField("name")
    private String name;

    /**
     * 活动介绍
     */
    @TableField("description")
    private String description;

    /**
     * 活动状态：0-未开始 1-进行中 2-已结束 3-已取消
     */
    @TableField("status")
    private Integer status;

    /**
     * 开始时间
     */
    @TableField("start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private Date endTime;

    /**
     * 最大参与次数
     */
    @TableField("max_join")
    private Integer maxJoin;

    @TableField("max_join_rule")
    private Integer maxJoinRule;

    /**
     * 最大中奖次数
     */
    @TableField("max_win")
    private Integer maxWin;

    /**
     * 消费金额
     */
    @TableField("consume_amount")
    private BigDecimal consumeAmount;

    /**
     * 消费积分
     */
    @TableField("consume_integral")
    private Integer consumeIntegral;

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
