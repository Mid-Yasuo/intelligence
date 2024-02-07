package com.einstein.albert.intelligence.entity.vo.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/1/9
 */
@Data
public class AwardItemReq {


    /**
     * 奖品 ID
     */
    private Long awardId;

    /**
     * 奖项名称
     */
    private String name;

    /**
     * 奖项数量
     */
    private Integer quantity;

    /**
     * 奖项级别
     */
    private Integer level;

    /**
     * 中奖概率
     */
    private BigDecimal probability;
}
