package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 黑名单对象 az_phone_black
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzPhoneBlack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 来源 */
    @Excel(name = "来源")
    private Integer source;

    /** 类型 */
    @Excel(name = "类型")
    private String dbType;

    /** 号码类型 */
    @Excel(name = "号码类型")
    private Integer phoneType;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createUserId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSource(Integer source) 
    {
        this.source = source;
    }

    public Integer getSource() 
    {
        return source;
    }
    public void setDbType(String dbType) 
    {
        this.dbType = dbType;
    }

    public String getDbType() 
    {
        return dbType;
    }
    public void setPhoneType(Integer phoneType) 
    {
        this.phoneType = phoneType;
    }

    public Integer getPhoneType() 
    {
        return phoneType;
    }
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("phone", getPhone())
            .append("status", getStatus())
            .append("source", getSource())
            .append("dbType", getDbType())
            .append("phoneType", getPhoneType())
            .append("createTime", getCreateTime())
            .append("createUserId", getCreateUserId())
            .toString();
    }
}
