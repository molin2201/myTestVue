package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzCallhisMapper;
import com.ruoyi.system.domain.AzCallhis;
import com.ruoyi.system.service.IAzCallhisService;

/**
 * 外呼记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-10
 */
@Service
public class AzCallhisServiceImpl implements IAzCallhisService 
{
    @Autowired
    private AzCallhisMapper azCallhisMapper;

    /**
     * 查询外呼记录
     * 
     * @param id 外呼记录ID
     * @return 外呼记录
     */
    @Override
    public AzCallhis selectAzCallhisById(Long id)
    {
        return azCallhisMapper.selectAzCallhisById(id);
    }

    /**
     * 查询外呼记录列表
     * 
     * @param azCallhis 外呼记录
     * @return 外呼记录
     */
    @Override
    public List<AzCallhis> selectAzCallhisList(AzCallhis azCallhis)
    {
        return azCallhisMapper.selectAzCallhisList(azCallhis);
    }

    /**
     * 新增外呼记录
     * 
     * @param azCallhis 外呼记录
     * @return 结果
     */
    @Override
    public int insertAzCallhis(AzCallhis azCallhis)
    {
        return azCallhisMapper.insertAzCallhis(azCallhis);
    }

    /**
     * 修改外呼记录
     * 
     * @param azCallhis 外呼记录
     * @return 结果
     */
    @Override
    public int updateAzCallhis(AzCallhis azCallhis)
    {
        azCallhis.setUpdateTime(DateUtils.getNowDate());
        return azCallhisMapper.updateAzCallhis(azCallhis);
    }

    /**
     * 批量删除外呼记录
     * 
     * @param ids 需要删除的外呼记录ID
     * @return 结果
     */
    @Override
    public int deleteAzCallhisByIds(Long[] ids)
    {
        return azCallhisMapper.deleteAzCallhisByIds(ids);
    }

    /**
     * 删除外呼记录信息
     * 
     * @param id 外呼记录ID
     * @return 结果
     */
    @Override
    public int deleteAzCallhisById(Long id)
    {
        return azCallhisMapper.deleteAzCallhisById(id);
    }
}
