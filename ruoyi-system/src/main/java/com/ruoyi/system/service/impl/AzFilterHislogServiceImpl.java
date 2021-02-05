package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzFilterHislogMapper;
import com.ruoyi.system.domain.AzFilterHislog;
import com.ruoyi.system.service.IAzFilterHislogService;

/**
 * 拦截日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzFilterHislogServiceImpl implements IAzFilterHislogService 
{
    @Autowired
    private AzFilterHislogMapper azFilterHislogMapper;

    /**
     * 查询拦截日志
     * 
     * @param id 拦截日志ID
     * @return 拦截日志
     */
    @Override
    public AzFilterHislog selectAzFilterHislogById(Long id)
    {
        return azFilterHislogMapper.selectAzFilterHislogById(id);
    }

    /**
     * 查询拦截日志列表
     * 
     * @param azFilterHislog 拦截日志
     * @return 拦截日志
     */
    @Override
    public List<AzFilterHislog> selectAzFilterHislogList(AzFilterHislog azFilterHislog)
    {
        return azFilterHislogMapper.selectAzFilterHislogList(azFilterHislog);
    }

    /**
     * 新增拦截日志
     * 
     * @param azFilterHislog 拦截日志
     * @return 结果
     */
    @Override
    public int insertAzFilterHislog(AzFilterHislog azFilterHislog)
    {
        return azFilterHislogMapper.insertAzFilterHislog(azFilterHislog);
    }

    /**
     * 修改拦截日志
     * 
     * @param azFilterHislog 拦截日志
     * @return 结果
     */
    @Override
    public int updateAzFilterHislog(AzFilterHislog azFilterHislog)
    {
        return azFilterHislogMapper.updateAzFilterHislog(azFilterHislog);
    }

    /**
     * 批量删除拦截日志
     * 
     * @param ids 需要删除的拦截日志ID
     * @return 结果
     */
    @Override
    public int deleteAzFilterHislogByIds(Long[] ids)
    {
        return azFilterHislogMapper.deleteAzFilterHislogByIds(ids);
    }

    /**
     * 删除拦截日志信息
     * 
     * @param id 拦截日志ID
     * @return 结果
     */
    @Override
    public int deleteAzFilterHislogById(Long id)
    {
        return azFilterHislogMapper.deleteAzFilterHislogById(id);
    }
}
