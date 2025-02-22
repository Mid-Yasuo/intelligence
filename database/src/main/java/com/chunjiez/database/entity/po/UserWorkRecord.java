package com.chunjiez.database.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户打卡记录表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2025-02-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_work_record")
public class UserWorkRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("username")
    private String username;

    /**
     * yyyyMM
     */
    @TableField("work_month")
    private Integer workMonth;

    /**
     * yyyyMMdd
     */
    @TableField("work_day")
    private Integer workDay;

    /**
     * 上班打卡时间
     */
    @TableField("up_time")
    private Date upTime;

    /**
     * 迟到时间，单位:秒
     */
    @TableField("late_time")
    private Long lateTime;

    @TableField("down_time")
    private Date downTime;

    /**
     * 打卡状态：1-正常 2-迟到 3-早退 4-旷工 5-缺勤 6-请假
     */
    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
