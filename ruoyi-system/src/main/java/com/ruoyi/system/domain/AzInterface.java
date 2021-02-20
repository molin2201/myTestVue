package com.ruoyi.system.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 接口配置对象 az_interface
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzInterface extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 接口名称 */
    @Excel(name = "接口名称")
    private String name;

    /** 接口地址 */
    @Excel(name = "接口地址")
    private String address;

    /** 命中次数 */
    @Excel(name = "命中次数")
    private Long hitCount;

    /** 使用用户数 */
    @Excel(name = "使用用户数")
    private Long userCount;

    /** 剩余调用次数 */
    @Excel(name = "剩余调用次数")
    private Long balCount;

    /** 费率 */
    @Excel(name = "费率")
    private BigDecimal rateAmt;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 超时时长 */
    @Excel(name = "超时时长")
    private Long timeout;

    /** 版本 */
    @Excel(name = "版本")
    private String version;

    /** 调用次数 */
    @Excel(name = "调用次数")
    private Long callCount;

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
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setHitCount(Long hitCount) 
    {
        this.hitCount = hitCount;
    }

    public Long getHitCount() 
    {
        return hitCount;
    }
    public void setUserCount(Long userCount) 
    {
        this.userCount = userCount;
    }

    public Long getUserCount() 
    {
        return userCount;
    }
    public void setBalCount(Long balCount) 
    {
        this.balCount = balCount;
    }

    public Long getCallCount() {
        return callCount;
    }

    public void setCallCount(Long callCount) {
        this.callCount = callCount;
    }

    public Long getBalCount()
    {
        return balCount;
    }
    public void setRateAmt(BigDecimal rateAmt) 
    {
        this.rateAmt = rateAmt;
    }

    public BigDecimal getRateAmt() 
    {
        return rateAmt;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setTimeout(Long timeout) 
    {
        this.timeout = timeout;
    }

    public Long getTimeout() 
    {
        return timeout;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("address", getAddress())
            .append("hitCount", getHitCount())
            .append("userCount", getUserCount())
            .append("balCount", getBalCount())
            .append("rateAmt", getRateAmt())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("timeout", getTimeout())
            .append("version", getVersion())
                .append("callCount", getCallCount())
            .toString();
    }
}
