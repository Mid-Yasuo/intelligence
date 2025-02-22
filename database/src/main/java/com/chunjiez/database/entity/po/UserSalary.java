package com.chunjiez.database.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户薪资表
 * </p>
 *
 * @author ZhangChunjie
 * @since 2025-02-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("user_salary")
public class UserSalary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("username")
    private String username;

    @TableField("department_id")
    private Long departmentId;

    @TableField("department_name")
    private String departmentName;

    /**
     * 基本工资
     */
    @TableField("base_salary")
    private BigDecimal baseSalary;

    /**
     * 绩效薪资
     */
    @TableField("performance_salary")
    private BigDecimal performanceSalary;

    /**
     * 交通补贴
     */
    @TableField("traffic_salary")
    private BigDecimal trafficSalary;

    /**
     * 奖金
     */
    @TableField("bonus")
    private BigDecimal bonus;

    /**
     * 养老金基数
     */
    @TableField("pension_base")
    private Long pensionBase;

    /**
     * 养老金比例
     */
    @TableField("pension_percent")
    private BigDecimal pensionPercent;

    /**
     * 医疗保险基数
     */
    @TableField("medical_base")
    private Long medicalBase;

    /**
     * 医疗保险比率
     */
    @TableField("medical_percent")
    private BigDecimal medicalPercent;

    /**
     * 公积金基数
     */
    @TableField("accumulation_fund_base")
    private Long accumulationFundBase;

    /**
     * 公积金比例
     */
    @TableField("accumulation_fund_percent")
    private BigDecimal accumulationFundPercent;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
