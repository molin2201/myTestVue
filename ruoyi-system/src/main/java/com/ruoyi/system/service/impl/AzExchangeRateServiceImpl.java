package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzExchangeRateMapper;
import com.ruoyi.system.domain.AzExchangeRate;
import com.ruoyi.system.service.IAzExchangeRateService;

/**
 * 费率管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzExchangeRateServiceImpl implements IAzExchangeRateService 
{
    @Autowired
    private AzExchangeRateMapper azExchangeRateMapper;

    /**
     * 查询费率管理
     * 
     * @param id 费率管理ID
     * @return 费率管理
     */
    @Override
    public AzExchangeRate selectAzExchangeRateById(Long id)
    {
        return azExchangeRateMapper.selectAzExchangeRateById(id);
    }

    /**
     * 查询费率管理列表
     * 
     * @param azExchangeRate 费率管理
     * @return 费率管理
     */
    @Override
    public List<AzExchangeRate> selectAzExchangeRateList(AzExchangeRate azExchangeRate)
    {
        return azExchangeRateMapper.selectAzExchangeRateList(azExchangeRate);
    }

    /**
     * 新增费率管理
     * 
     * @param azExchangeRate 费率管理
     * @return 结果
     */
    @Override
    public int insertAzExchangeRate(AzExchangeRate azExchangeRate)
    {
        return azExchangeRateMapper.insertAzExchangeRate(azExchangeRate);
    }

    /**
     * 修改费率管理
     * 
     * @param azExchangeRate 费率管理
     * @return 结果
     */
    @Override
    public int updateAzExchangeRate(AzExchangeRate azExchangeRate)
    {
        azExchangeRate.setUpdateTime(DateUtils.getNowDate());
        return azExchangeRateMapper.updateAzExchangeRate(azExchangeRate);
    }

    /**
     * 批量删除费率管理
     * 
     * @param ids 需要删除的费率管理ID
     * @return 结果
     */
    @Override
    public int deleteAzExchangeRateByIds(Long[] ids)
    {
        return azExchangeRateMapper.deleteAzExchangeRateByIds(ids);
    }

    /**
     * 删除费率管理信息
     * 
     * @param id 费率管理ID
     * @return 结果
     */
    @Override
    public int deleteAzExchangeRateById(Long id)
    {
        return azExchangeRateMapper.deleteAzExchangeRateById(id);
    }
}
