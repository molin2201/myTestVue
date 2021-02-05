package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AzInterfaceMapper;
import com.ruoyi.system.domain.AzInterface;
import com.ruoyi.system.service.IAzInterfaceService;

/**
 * 接口配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-02-03
 */
@Service
public class AzInterfaceServiceImpl implements IAzInterfaceService 
{
    @Autowired
    private AzInterfaceMapper azInterfaceMapper;

    /**
     * 查询接口配置
     * 
     * @param id 接口配置ID
     * @return 接口配置
     */
    @Override
    public AzInterface selectAzInterfaceById(Long id)
    {
        return azInterfaceMapper.selectAzInterfaceById(id);
    }

    /**
     * 查询接口配置列表
     * 
     * @param azInterface 接口配置
     * @return 接口配置
     */
    @Override
    public List<AzInterface> selectAzInterfaceList(AzInterface azInterface)
    {
        return azInterfaceMapper.selectAzInterfaceList(azInterface);
    }

    /**
     * 新增接口配置
     * 
     * @param azInterface 接口配置
     * @return 结果
     */
    @Override
    public int insertAzInterface(AzInterface azInterface)
    {
        azInterface.setCreateTime(DateUtils.getNowDate());
        return azInterfaceMapper.insertAzInterface(azInterface);
    }

    /**
     * 修改接口配置
     * 
     * @param azInterface 接口配置
     * @return 结果
     */
    @Override
    public int updateAzInterface(AzInterface azInterface)
    {
        return azInterfaceMapper.updateAzInterface(azInterface);
    }

    /**
     * 批量删除接口配置
     * 
     * @param ids 需要删除的接口配置ID
     * @return 结果
     */
    @Override
    public int deleteAzInterfaceByIds(Long[] ids)
    {
        return azInterfaceMapper.deleteAzInterfaceByIds(ids);
    }

    /**
     * 删除接口配置信息
     * 
     * @param id 接口配置ID
     * @return 结果
     */
    @Override
    public int deleteAzInterfaceById(Long id)
    {
        return azInterfaceMapper.deleteAzInterfaceById(id);
    }
}
