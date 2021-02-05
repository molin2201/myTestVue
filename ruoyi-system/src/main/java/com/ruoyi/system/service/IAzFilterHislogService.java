package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzFilterHislog;

/**
 * 拦截日志Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzFilterHislogService 
{
    /**
     * 查询拦截日志
     * 
     * @param id 拦截日志ID
     * @return 拦截日志
     */
    public AzFilterHislog selectAzFilterHislogById(Long id);

    /**
     * 查询拦截日志列表
     * 
     * @param azFilterHislog 拦截日志
     * @return 拦截日志集合
     */
    public List<AzFilterHislog> selectAzFilterHislogList(AzFilterHislog azFilterHislog);

    /**
     * 新增拦截日志
     * 
     * @param azFilterHislog 拦截日志
     * @return 结果
     */
    public int insertAzFilterHislog(AzFilterHislog azFilterHislog);

    /**
     * 修改拦截日志
     * 
     * @param azFilterHislog 拦截日志
     * @return 结果
     */
    public int updateAzFilterHislog(AzFilterHislog azFilterHislog);

    /**
     * 批量删除拦截日志
     * 
     * @param ids 需要删除的拦截日志ID
     * @return 结果
     */
    public int deleteAzFilterHislogByIds(Long[] ids);

    /**
     * 删除拦截日志信息
     * 
     * @param id 拦截日志ID
     * @return 结果
     */
    public int deleteAzFilterHislogById(Long id);
}
