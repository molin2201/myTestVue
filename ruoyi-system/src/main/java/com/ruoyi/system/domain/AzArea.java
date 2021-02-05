package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 地区信息对象 az_area
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 地区前缀 */
    @Excel(name = "地区前缀")
    private String priCode;

    /** 地区编码 */
    @Excel(name = "地区编码")
    private String code;

    /** 地区名称 */
    @Excel(name = "地区名称")
    private String name;

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
    public void setPriCode(String priCode) 
    {
        this.priCode = priCode;
    }

    public String getPriCode() 
    {
        return priCode;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
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
            .append("priCode", getPriCode())
            .append("code", getCode())
            .append("name", getName())
            .append("remark", getRemark())
            .append("updateTime", getUpdateTime())
            .append("updateUserId", getUpdateUserId())
            .toString();
    }
}
