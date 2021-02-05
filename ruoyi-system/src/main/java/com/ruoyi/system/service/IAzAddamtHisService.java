package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzAddamtHis;

/**
 * 充值记录Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzAddamtHisService 
{
    /**
     * 查询充值记录
     * 
     * @param id 充值记录ID
     * @return 充值记录
     */
    public AzAddamtHis selectAzAddamtHisById(Long id);

    /**
     * 查询充值记录列表
     * 
     * @param azAddamtHis 充值记录
     * @return 充值记录集合
     */
    public List<AzAddamtHis> selectAzAddamtHisList(AzAddamtHis azAddamtHis);

    /**
     * 新增充值记录
     * 
     * @param azAddamtHis 充值记录
     * @return 结果
     */
    public int insertAzAddamtHis(AzAddamtHis azAddamtHis);

    /**
     * 修改充值记录
     * 
     * @param azAddamtHis 充值记录
     * @return 结果
     */
    public int updateAzAddamtHis(AzAddamtHis azAddamtHis);

    /**
     * 批量删除充值记录
     * 
     * @param ids 需要删除的充值记录ID
     * @return 结果
     */
    public int deleteAzAddamtHisByIds(Long[] ids);

    /**
     * 删除充值记录信息
     * 
     * @param id 充值记录ID
     * @return 结果
     */
    public int deleteAzAddamtHisById(Long id);
}
