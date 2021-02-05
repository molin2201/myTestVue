package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzAddamtHisMapper;
import com.ruoyi.system.domain.AzAddamtHis;
import com.ruoyi.system.service.IAzAddamtHisService;

/**
 * 充值记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzAddamtHisServiceImpl implements IAzAddamtHisService 
{
    @Autowired
    private AzAddamtHisMapper azAddamtHisMapper;

    /**
     * 查询充值记录
     * 
     * @param id 充值记录ID
     * @return 充值记录
     */
    @Override
    public AzAddamtHis selectAzAddamtHisById(Long id)
    {
        return azAddamtHisMapper.selectAzAddamtHisById(id);
    }

    /**
     * 查询充值记录列表
     * 
     * @param azAddamtHis 充值记录
     * @return 充值记录
     */
    @Override
    public List<AzAddamtHis> selectAzAddamtHisList(AzAddamtHis azAddamtHis)
    {
        return azAddamtHisMapper.selectAzAddamtHisList(azAddamtHis);
    }

    /**
     * 新增充值记录
     * 
     * @param azAddamtHis 充值记录
     * @return 结果
     */
    @Override
    public int insertAzAddamtHis(AzAddamtHis azAddamtHis)
    {
        azAddamtHis.setCreateTime(DateUtils.getNowDate());
        return azAddamtHisMapper.insertAzAddamtHis(azAddamtHis);
    }

    /**
     * 修改充值记录
     * 
     * @param azAddamtHis 充值记录
     * @return 结果
     */
    @Override
    public int updateAzAddamtHis(AzAddamtHis azAddamtHis)
    {
        return azAddamtHisMapper.updateAzAddamtHis(azAddamtHis);
    }

    /**
     * 批量删除充值记录
     * 
     * @param ids 需要删除的充值记录ID
     * @return 结果
     */
    @Override
    public int deleteAzAddamtHisByIds(Long[] ids)
    {
        return azAddamtHisMapper.deleteAzAddamtHisByIds(ids);
    }

    /**
     * 删除充值记录信息
     * 
     * @param id 充值记录ID
     * @return 结果
     */
    @Override
    public int deleteAzAddamtHisById(Long id)
    {
        return azAddamtHisMapper.deleteAzAddamtHisById(id);
    }
}
