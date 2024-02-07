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
 * 领奖记录表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2024-01-06
 */
@Data
@Accessors(chain = true)
@TableName("biz_receive_award_record")
public class ReceiveAwardRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 抽奖记录 ID
     */
    @TableField("lottery_record_id")
    private Long lotteryRecordId;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 收货地址
     */
    @TableField("address")
    private String address;

    /**
     * 领奖状态：0-领取中 1-已领取 
     */
    @TableField("status")
    private Integer status;

    /**
     * 领奖时间
     */
    @TableField("receive_time")
    private Date receiveTime;

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
