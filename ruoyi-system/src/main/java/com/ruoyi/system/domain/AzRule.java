package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 拦截规则对象 az_rule
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzRule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 拦截名称 */
    @Excel(name = "拦截名称")
    private String name;

    /** 拦截类型 */
    @Excel(name = "拦截类型")
    private Integer ruleType;

    /** 地区 */
    @Excel(name = "地区")
    private String areaId;

    /** 运营商 */
    @Excel(name = "运营商")
    private String companyId;

    /** 号码规则 */
    @Excel(name = "号码规则")
    private String numruleId;

    /** 限制天数 */
    @Excel(name = "限制天数")
    private Long limitDay;

    /** 备用1 */
    @Excel(name = "备用1")
    private String field1;

    /** 备用2 */
    @Excel(name = "备用2")
    private String field2;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long createUserId;

    /** 是否启用 */
    @Excel(name = "是否启用")
    private String status;

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
    public void setRuleType(Integer ruleType) 
    {
        this.ruleType = ruleType;
    }

    public Integer getRuleType() 
    {
        return ruleType;
    }
    public void setAreaId(String areaId) 
    {
        this.areaId = areaId;
    }

    public String getAreaId() 
    {
        return areaId;
    }
    public void setCompanyId(String companyId) 
    {
        this.companyId = companyId;
    }

    public String getCompanyId() 
    {
        return companyId;
    }
    public void setNumruleId(String numruleId) 
    {
        this.numruleId = numruleId;
    }

    public String getNumruleId() 
    {
        return numruleId;
    }
    public void setLimitDay(Long limitDay) 
    {
        this.limitDay = limitDay;
    }

    public Long getLimitDay() 
    {
        return limitDay;
    }
    public void setField1(String field1) 
    {
        this.field1 = field1;
    }

    public String getField1() 
    {
        return field1;
    }
    public void setField2(String field2) 
    {
        this.field2 = field2;
    }

    public String getField2() 
    {
        return field2;
    }
    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("remark", getRemark())
            .append("ruleType", getRuleType())
            .append("areaId", getAreaId())
            .append("companyId", getCompanyId())
            .append("numruleId", getNumruleId())
            .append("limitDay", getLimitDay())
            .append("field1", getField1())
            .append("field2", getField2())
            .append("createUserId", getCreateUserId())
            .append("createTime", getCreateTime())
            .append("status", getStatus())
            .toString();
    }
}
