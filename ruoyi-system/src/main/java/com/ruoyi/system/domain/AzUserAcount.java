package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户账户对象 az_user_acount
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzUserAcount extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal balAmt;

    /** 充值总金额 */
    @Excel(name = "充值总金额")
    private BigDecimal addAmt;

    /** 消费总金额 */
    @Excel(name = "消费总金额")
    private BigDecimal consumeAmt;

    /** 利润 */
    @Excel(name = "利润")
    private BigDecimal profitAmt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setBalAmt(BigDecimal balAmt) 
    {
        this.balAmt = balAmt;
    }

    public BigDecimal getBalAmt() 
    {
        return balAmt;
    }
    public void setAddAmt(BigDecimal addAmt) 
    {
        this.addAmt = addAmt;
    }

    public BigDecimal getAddAmt() 
    {
        return addAmt;
    }
    public void setConsumeAmt(BigDecimal consumeAmt) 
    {
        this.consumeAmt = consumeAmt;
    }

    public BigDecimal getConsumeAmt() 
    {
        return consumeAmt;
    }
    public void setProfitAmt(BigDecimal profitAmt) 
    {
        this.profitAmt = profitAmt;
    }

    public BigDecimal getProfitAmt() 
    {
        return profitAmt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("balAmt", getBalAmt())
            .append("addAmt", getAddAmt())
            .append("consumeAmt", getConsumeAmt())
            .append("profitAmt", getProfitAmt())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
