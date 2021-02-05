package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzAreaMapper;
import com.ruoyi.system.domain.AzArea;
import com.ruoyi.system.service.IAzAreaService;

/**
 * 地区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzAreaServiceImpl implements IAzAreaService 
{
    @Autowired
    private AzAreaMapper azAreaMapper;

    /**
     * 查询地区信息
     * 
     * @param id 地区信息ID
     * @return 地区信息
     */
    @Override
    public AzArea selectAzAreaById(Long id)
    {
        return azAreaMapper.selectAzAreaById(id);
    }

    /**
     * 查询地区信息列表
     * 
     * @param azArea 地区信息
     * @return 地区信息
     */
    @Override
    public List<AzArea> selectAzAreaList(AzArea azArea)
    {
        return azAreaMapper.selectAzAreaList(azArea);
    }

    /**
     * 新增地区信息
     * 
     * @param azArea 地区信息
     * @return 结果
     */
    @Override
    public int insertAzArea(AzArea azArea)
    {
        return azAreaMapper.insertAzArea(azArea);
    }

    /**
     * 修改地区信息
     * 
     * @param azArea 地区信息
     * @return 结果
     */
    @Override
    public int updateAzArea(AzArea azArea)
    {
        azArea.setUpdateTime(DateUtils.getNowDate());
        return azAreaMapper.updateAzArea(azArea);
    }

    /**
     * 批量删除地区信息
     * 
     * @param ids 需要删除的地区信息ID
     * @return 结果
     */
    @Override
    public int deleteAzAreaByIds(Long[] ids)
    {
        return azAreaMapper.deleteAzAreaByIds(ids);
    }

    /**
     * 删除地区信息信息
     * 
     * @param id 地区信息ID
     * @return 结果
     */
    @Override
    public int deleteAzAreaById(Long id)
    {
        return azAreaMapper.deleteAzAreaById(id);
    }
}
