package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 拦截日志对象 az_filter_hislog
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public class AzFilterHislog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 拦截类型 */
    @Excel(name = "拦截类型")
    private String filterType;

    /** 请求次数 */
    @Excel(name = "请求次数")
    private Long reqCount;

    /** 命中次数 */
    @Excel(name = "命中次数")
    private Long filterCount;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date insertDate;

    /** 接口 */
    @Excel(name = "接口")
    private String interfaceId;

    /** 地区 */
    @Excel(name = "地区")
    private String areaIn;

    /** 用户 */
    @Excel(name = "用户")
    private Long userId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFilterType(String filterType) 
    {
        this.filterType = filterType;
    }

    public String getFilterType() 
    {
        return filterType;
    }
    public void setReqCount(Long reqCount) 
    {
        this.reqCount = reqCount;
    }

    public Long getReqCount() 
    {
        return reqCount;
    }
    public void setFilterCount(Long filterCount) 
    {
        this.filterCount = filterCount;
    }

    public Long getFilterCount() 
    {
        return filterCount;
    }
    public void setInsertDate(Date insertDate) 
    {
        this.insertDate = insertDate;
    }

    public Date getInsertDate() 
    {
        return insertDate;
    }
    public void setInterfaceId(String interfaceId) 
    {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceId() 
    {
        return interfaceId;
    }
    public void setAreaIn(String areaIn) 
    {
        this.areaIn = areaIn;
    }

    public String getAreaIn() 
    {
        return areaIn;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("filterType", getFilterType())
            .append("reqCount", getReqCount())
            .append("filterCount", getFilterCount())
            .append("insertDate", getInsertDate())
            .append("interfaceId", getInterfaceId())
            .append("areaIn", getAreaIn())
            .append("userId", getUserId())
            .toString();
    }
}
