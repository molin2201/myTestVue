package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充值记录对象 az_addamt_his
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzAddamtHis extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 次数 */
    @Excel(name = "次数")
    private Long count;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 总价 */
    @Excel(name = "总价")
    private BigDecimal amt;

    /** 充值人 */
    @Excel(name = "充值人")
    private Long createUserId;

    /** 操作人 */
    @Excel(name = "操作人")
    private Long optUserId;

    /** 接口ID */
    @Excel(name = "接口ID")
    private String interfaceId;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private Long payType;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 订单状态 */
    @Excel(name = "订单状态")
    private Long orderSatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setUnitPrice(BigDecimal unitPrice) 
    {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getUnitPrice() 
    {
        return unitPrice;
    }
    public void setAmt(BigDecimal amt) 
    {
        this.amt = amt;
    }

    public BigDecimal getAmt() 
    {
        return amt;
    }
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
    }
    public void setOptUserId(Long optUserId) 
    {
        this.optUserId = optUserId;
    }

    public Long getOptUserId() 
    {
        return optUserId;
    }
    public void setInterfaceId(String interfaceId) 
    {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceId() 
    {
        return interfaceId;
    }
    public void setPayType(Long payType) 
    {
        this.payType = payType;
    }

    public Long getPayType() 
    {
        return payType;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setOrderSatus(Long orderSatus) 
    {
        this.orderSatus = orderSatus;
    }

    public Long getOrderSatus() 
    {
        return orderSatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createTime", getCreateTime())
            .append("count", getCount())
            .append("unitPrice", getUnitPrice())
            .append("amt", getAmt())
            .append("createUserId", getCreateUserId())
            .append("remark", getRemark())
            .append("optUserId", getOptUserId())
            .append("interfaceId", getInterfaceId())
            .append("payType", getPayType())
            .append("orderNo", getOrderNo())
            .append("orderSatus", getOrderSatus())
            .toString();
    }
}
