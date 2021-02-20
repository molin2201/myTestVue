package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.AzCallhis;

/**
 * 外呼记录Mapper接口
 * 
 * @author ruoyi
 * @date 2021-02-10
 */
public interface AzCallhisMapper 
{
    /**
     * 查询外呼记录
     * 
     * @param id 外呼记录ID
     * @return 外呼记录
     */
    public AzCallhis selectAzCallhisById(Long id);

    /**
     * 查询外呼记录列表
     * 
     * @param azCallhis 外呼记录
     * @return 外呼记录集合
     */
    public List<AzCallhis> selectAzCallhisList(AzCallhis azCallhis);

    /**
     * 新增外呼记录
     * 
     * @param azCallhis 外呼记录
     * @return 结果
     */
    public int insertAzCallhis(AzCallhis azCallhis);

    /**
     * 修改外呼记录
     * 
     * @param azCallhis 外呼记录
     * @return 结果
     */
    public int updateAzCallhis(AzCallhis azCallhis);

    /**
     * 删除外呼记录
     * 
     * @param id 外呼记录ID
     * @return 结果
     */
    public int deleteAzCallhisById(Long id);

    /**
     * 批量删除外呼记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAzCallhisByIds(Long[] ids);
}
