package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 手机地区对象 az_phonearea
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzPhonearea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 手机前缀 */
    @Excel(name = "手机前缀")
    private String prePhone;

    /** 地区区号 */
    @Excel(name = "地区区号")
    private String areaCode;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String areaName;

    /** 运营商 */
    @Excel(name = "运营商")
    private String company;

    /** 创建人 */
    @Excel(name = "创建人")
    private String updateUserId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPrePhone(String prePhone) 
    {
        this.prePhone = prePhone;
    }

    public String getPrePhone() 
    {
        return prePhone;
    }
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode() 
    {
        return areaCode;
    }
    public void setAreaName(String areaName) 
    {
        this.areaName = areaName;
    }

    public String getAreaName() 
    {
        return areaName;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
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
            .append("prePhone", getPrePhone())
            .append("areaCode", getAreaCode())
            .append("areaName", getAreaName())
            .append("company", getCompany())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .toString();
    }
}
