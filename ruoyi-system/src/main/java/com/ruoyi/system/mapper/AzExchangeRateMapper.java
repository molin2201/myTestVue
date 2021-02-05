package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AzExchangeRate;

/**
 * 费率管理Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface AzExchangeRateMapper 
{
    /**
     * 查询费率管理
     * 
     * @param id 费率管理ID
     * @return 费率管理
     */
    public AzExchangeRate selectAzExchangeRateById(Long id);

    /**
     * 查询费率管理列表
     * 
     * @param azExchangeRate 费率管理
     * @return 费率管理集合
     */
    public List<AzExchangeRate> selectAzExchangeRateList(AzExchangeRate azExchangeRate);

    /**
     * 新增费率管理
     * 
     * @param azExchangeRate 费率管理
     * @return 结果
     */
    public int insertAzExchangeRate(AzExchangeRate azExchangeRate);

    /**
     * 修改费率管理
     * 
     * @param azExchangeRate 费率管理
     * @return 结果
     */
    public int updateAzExchangeRate(AzExchangeRate azExchangeRate);

    /**
     * 删除费率管理
     * 
     * @param id 费率管理ID
     * @return 结果
     */
    public int deleteAzExchangeRateById(Long id);

    /**
     * 批量删除费率管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAzExchangeRateByIds(Long[] ids);
}
