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
 * 奖品表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Data
@Accessors(chain = true)
@TableName("biz_award")
public class Award implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 奖品名称
     */
    @TableField("name")
    private String name;

    /**
     * 库存
     */
    @TableField("inventory")
    private Integer inventory;

    /**
     * 金额
     */
    @TableField("amount")
    private BigDecimal amount;

    /**
     * 奖品类型
     */
    @TableField("kind")
    private Integer kind;

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
