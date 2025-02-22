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
@TableName("user_salary_record")
public class UserSalaryRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("user_id")
    private Long userId;

    @TableField("username")
    private String username;

    /**
     * 月份
     */
    @TableField("salary_month")
    private Integer salaryMonth;

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
     * 养老金
     */
    @TableField("pension_salary")
    private BigDecimal pensionSalary;

    /**
     * 医疗保险
     */
    @TableField("medical_salary")
    private BigDecimal medicalSalary;

    /**
     * 公积金（个人）
     */
    @TableField("accumulation_fund_salary")
    private BigDecimal accumulationFundSalary;

    /**
     * 公积金（企业）
     */
    @TableField("accumulation_fund_bonus")
    private BigDecimal accumulationFundBonus;

    /**
     * 罚款金额
     */
    @TableField("fine_salary")
    private BigDecimal fineSalary;

    /**
     * 罚款备注
     */
    @TableField("fine_remark")
    private String fineRemark;

    /**
     * 总额
     */
    @TableField("total_salary")
    private BigDecimal totalSalary;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
