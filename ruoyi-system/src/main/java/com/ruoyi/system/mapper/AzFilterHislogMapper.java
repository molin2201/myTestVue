package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AzFilterHislog;

/**
 * 拦截日志Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface AzFilterHislogMapper 
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
     * 删除拦截日志
     * 
     * @param id 拦截日志ID
     * @return 结果
     */
    public int deleteAzFilterHislogById(Long id);

    /**
     * 批量删除拦截日志
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAzFilterHislogByIds(Long[] ids);
}
