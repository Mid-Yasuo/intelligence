package com.einstein.albert.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 奖项表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Data
@Accessors(chain = true)
@TableName("biz_award_item")
public class AwardItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("activity_id")
    private Long activityId;

    /**
     * 奖品 ID
     */
    @TableField("award_id")
    private Long awardId;

    /**
     * 奖项名称
     */
    @TableField("name")
    private String name;

    /**
     * 奖项数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * 奖项级别
     */
    @TableField("level")
    private Integer level;

    /**
     * 中奖概率
     */
    @TableField("probability")
    private BigDecimal probability;

}
