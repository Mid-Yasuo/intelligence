package com.einstein.albert.intelligence.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户钱包表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Data
@Accessors(chain = true)
@TableName("biz_user_wallet")
public class UserWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户 ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;

    /**
     * 总金额
     */
    @TableField("total_amount")
    private BigDecimal totalAmount;

    /**
     * 余额
     */
    @TableField("balance_amount")
    private BigDecimal balanceAmount;

    /**
     * 积分
     */
    @TableField("integral")
    private Long integral;

    /**
     * 状态：1-可用；2-冻结；3-销户
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人 ID
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;

    /**
     * 最新修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 最新修改人 ID
     */
    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private Long updater;


}
