package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.AzArea;

/**
 * 地区信息Service接口
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
public interface IAzAreaService 
{
    /**
     * 查询地区信息
     * 
     * @param id 地区信息ID
     * @return 地区信息
     */
    public AzArea selectAzAreaById(Long id);

    /**
     * 查询地区信息列表
     * 
     * @param azArea 地区信息
     * @return 地区信息集合
     */
    public List<AzArea> selectAzAreaList(AzArea azArea);

    /**
     * 新增地区信息
     * 
     * @param azArea 地区信息
     * @return 结果
     */
    public int insertAzArea(AzArea azArea);

    /**
     * 修改地区信息
     * 
     * @param azArea 地区信息
     * @return 结果
     */
    public int updateAzArea(AzArea azArea);

    /**
     * 批量删除地区信息
     * 
     * @param ids 需要删除的地区信息ID
     * @return 结果
     */
    public int deleteAzAreaByIds(Long[] ids);

    /**
     * 删除地区信息信息
     * 
     * @param id 地区信息ID
     * @return 结果
     */
    public int deleteAzAreaById(Long id);
}
