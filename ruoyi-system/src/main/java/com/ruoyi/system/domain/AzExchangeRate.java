package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 费率管理对象 az_exchange_rate
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzExchangeRate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 费率名称 */
    @Excel(name = "费率名称")
    private String name;

    /** 计费单价(元) */
    @Excel(name = "计费单价(元)")
    private BigDecimal unitPrice;

    /** 活动说明 */
    @Excel(name = "活动说明")
    private String favourable;

    /** 最低起充额度 */
    @Excel(name = "最低起充额度")
    private BigDecimal minPrice;

    /** 活动金额 */
    @Excel(name = "活动金额")
    private BigDecimal minAmt;

    /** 赠送金额 */
    @Excel(name = "赠送金额")
    private BigDecimal addAmt;

    /** 活动类型 */
    @Excel(name = "活动类型")
    private Integer type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setFavourable(String favourable) 
    {
        this.favourable = favourable;
    }

    public String getFavourable() 
    {
        return favourable;
    }
    public void setMinPrice(BigDecimal minPrice) 
    {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinPrice() 
    {
        return minPrice;
    }
    public void setMinAmt(BigDecimal minAmt) 
    {
        this.minAmt = minAmt;
    }

    public BigDecimal getMinAmt() 
    {
        return minAmt;
    }
    public void setAddAmt(BigDecimal addAmt) 
    {
        this.addAmt = addAmt;
    }

    public BigDecimal getAddAmt() 
    {
        return addAmt;
    }
    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("unitPrice", getUnitPrice())
            .append("favourable", getFavourable())
            .append("minPrice", getMinPrice())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("minAmt", getMinAmt())
            .append("addAmt", getAddAmt())
            .append("type", getType())
            .toString();
    }
}
