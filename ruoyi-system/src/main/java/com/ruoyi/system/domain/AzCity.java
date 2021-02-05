package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 城市号码对象 az_city
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzCity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 地区区号 */
    @Excel(name = "地区区号")
    private String areaCode;

    /** 省份 */
    @Excel(name = "省份")
    private String province;

    /** 城市 */
    @Excel(name = "城市")
    private String city;

    /** 主叫号码长度 */
    @Excel(name = "主叫号码长度")
    private String callLength;

    /** 被叫号码长度 */
    @Excel(name = "被叫号码长度")
    private String bcallLength;

    /** 描述 */
    @Excel(name = "描述")
    private String desc;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String areaName;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updateUserId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode() 
    {
        return areaCode;
    }
    public void setProvince(String province) 
    {
        this.province = province;
    }

    public String getProvince() 
    {
        return province;
    }
    public void setCity(String city) 
    {
        this.city = city;
    }

    public String getCity() 
    {
        return city;
    }
    public void setCallLength(String callLength) 
    {
        this.callLength = callLength;
    }

    public String getCallLength() 
    {
        return callLength;
    }
    public void setBcallLength(String bcallLength) 
    {
        this.bcallLength = bcallLength;
    }

    public String getBcallLength() 
    {
        return bcallLength;
    }
    public void setDesc(String desc) 
    {
        this.desc = desc;
    }

    public String getDesc() 
    {
        return desc;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("areaCode", getAreaCode())
            .append("province", getProvince())
            .append("city", getCity())
            .append("callLength", getCallLength())
            .append("bcallLength", getBcallLength())
            .append("desc", getDesc())
            .append("areaName", getAreaName())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .toString();
    }
}
